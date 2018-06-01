package com.pknu.bbs.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pknu.bbs.common.Page;
import com.pknu.bbs.dao.BBSDao;
import com.pknu.bbs.dto.BBSDto;
import com.pknu.bbs.dto.FileDto;
import com.pknu.bbs.dto.UpdateDto;
import com.pknu.bbs.util.FileSaveHelper;

@Service
public class BBSServiceImpl implements BBSService {
	@Autowired
	BBSDao bbsDao;
	
	@Autowired
	@Qualifier("bbs")
	Page page;
	
	@Resource(name="saveDir")
	String saveDir;
	@Autowired
	FileSaveHelper fileSaveHelper;
	List<BBSDto> articleList;
	HashMap<String, Integer> paramMap;

	public Model list(int pageNum,Model model){	
		int totalCount=0;		
		int pageSize=10;//한페이지에 보여줄 글의 갯수
		int pageBlock=10;//한 블럭당 보여줄 페이지 갯수  	    

		totalCount=bbsDao.getArticleCount();			
		page.paging(pageNum,totalCount,pageSize, pageBlock);		
		paramMap = new HashMap<>();
		paramMap.put("startRow", page.getStartRow());
		paramMap.put("endRow", page.getEndRow());
		//		articleList=bbsDao.getArticles(page.getStartRow(),page.getEndRow());		
		articleList=bbsDao.getArticles(paramMap);		
		model.addAttribute("totalCount",totalCount);
		model.addAttribute("articleList",articleList);
		model.addAttribute("pageCode",page.getSb().toString());				

		return model;
	}		
	

	@Override
	public int getMessageCount(String id) {		
		return bbsDao.getMessageCount(id);
	}



	@Override
	public void getArticle(int articleNum,int fileStatus,Model model) {			
		model.addAttribute("article",bbsDao.getArticle(articleNum));		
		if(fileStatus==1){
			model.addAttribute("fileList", bbsDao.getFiles(articleNum));			
		}
	}		

	@Override
	public String login(String id, String pass,HttpSession session) {	
		String dbpass = bbsDao.login(id);

		String view=null;
		if(dbpass!=null){
			if(dbpass.equals(pass)){
				session.setAttribute("id", id);
				view="redirect:/list.bbs?pageNum=1";	
			}else{
				view="login/passFail";	
			}
		}else{
			view="join/joinMember";
		}		
		return view;
	}	

	@Override
	public void insertArticle(BBSDto article) {	
		if(article.getFileNames()==null){
			bbsDao.insertArticle(article);
		}else{				
			int articleNum=bbsDao.getNextArticleNum(); 
			article.setArticleNum(articleNum);
			article.setFileStatus((byte)1);
			bbsDao.insertArticle(article);	
			commonFileUpload(articleNum,article.getFileNames());
		}			
	}

	@Override
	public void delete(int articleNum,int fileStatus) {
		if(fileStatus==1){
			deleteFile(articleNum);		
		}			
		bbsDao.delete(articleNum);		
	}
	
	public void deleteFile(int articleNum){
		List<String> storedFnameList=bbsDao.getStoredFnames(articleNum);
		if(!storedFnameList.isEmpty()){
			for(String storedFname : storedFnameList){
				File file = new File(saveDir+storedFname);
				if(file.exists()){
					file.delete();
				}
			}
		}
	}

	@Override
	public void replyArticle(BBSDto article,MultipartFile springFname) {
		if(!springFname.isEmpty()){
			//			article.setFname(springFname.getOriginalFilename());			
			try {
				springFname.transferTo(new File(saveDir+springFname.getOriginalFilename()));
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
		bbsDao.replyArticle(article);		
	}

	@Override
	public void updateGetArticle(int articleNum, int fileStatus, Model model) {
		BBSDto article=bbsDao.updateGetArticle(articleNum);
		if(fileStatus==1){			
			model.addAttribute("fileList", bbsDao.getFiles(articleNum));
			model.addAttribute("fileCount", bbsDao.getFiles(articleNum).size());
		}else{
//			기존의 글이 파일 업로드가 없는 글이었을 경우는 updateForm.jsp의 fileCount에 공백값이
//			들어가므로 update.bbs 요청시 400에러가 남..그래서 편법으로 0 줬음
			model.addAttribute("fileCount", 0);
		}
		model.addAttribute("article", article);				
	}

	@Override
	public void updateArticle(BBSDto article,UpdateDto updateDto,
			MultipartHttpServletRequest mRequest, Model model,int fileCount) {
		List<MultipartFile> mfile=mRequest.getFiles("springFname"); 
//		새로운 파일이 업로드 없을경우
		if(mfile.get(0).isEmpty()){
//			기존에 업로드되어 있는 파일중 하나라도 삭제된경우
			if(updateDto.getStoredFnameList()!=null){						
				bbsDao.someDelFile(updateDto.getStoredFnameList());
				for(String storedFname : updateDto.getStoredFnameList()){
					updateSomeDelFile(storedFname);
				}				
				
				if(fileCount==updateDto.getStoredFnameList().size()){
					article.setFileStatus((byte)0);					
				}
			}
			bbsDao.updateArticle(article);
		}else{				
			if(updateDto.getStoredFnameList()!=null){				
				bbsDao.someDelFile(updateDto.getStoredFnameList());
				for(String storedFname : updateDto.getStoredFnameList()){
					updateSomeDelFile(storedFname);
				}
			}
							
			article.setFileStatus((byte)1);					
//			commonFileUpload(mfile,article.getArticleNum());
			bbsDao.updateArticle(article);			
		}
		model.addAttribute("articleNum", article.getArticleNum());
		model.addAttribute("fileStatus", article.getFileStatus());		
	}
		
	public void updateSomeDelFile(String storedFname){		
		if(!storedFname.isEmpty()){			
			File file = new File(saveDir+storedFname);
			if(file.exists()){
				file.delete();
			}			
		}
	}

	@Override
	public FileSystemResource download(HttpServletResponse resp, 
									   String storedFname) {		
		resp.setContentType("application/download");	
		String originFname=storedFname.substring(storedFname.indexOf("_")+1);;
		try{
			originFname = URLEncoder.encode(originFname,"utf-8").replace("+","%20").replace("%28", "(").replace("%29", ")");
		}catch(Exception e){

		}

		resp.setHeader("Content-Disposition", "attachment;" +" filename=\""+originFname+ "\";");
		FileSystemResource fsr= new FileSystemResource(saveDir+storedFname);
		return fsr;
	}		

	public void commonFileUpload(int articleNum, List<String> fileNames){
		FileDto fileDto= null;
	
		for(String storedFname: fileNames){					
			fileDto = new FileDto();			
			fileDto.setStoredFname(storedFname);			
			fileDto.setArticleNum(articleNum);
			bbsDao.insertFile(fileDto);				
		}
	}



}
