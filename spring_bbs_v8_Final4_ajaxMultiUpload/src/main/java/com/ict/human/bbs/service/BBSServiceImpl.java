package com.ict.human.bbs.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.ict.human.bbs.common.FileSaveHelper;
import com.ict.human.bbs.common.Page;
import com.ict.human.bbs.dao.BBSDao;
import com.ict.human.bbs.dto.BBSDto;
import com.ict.human.bbs.dto.FileDto;

@Service
public class BBSServiceImpl implements BBSService {

	@Autowired
	private BBSDao bbsDao;
	
	@Autowired
	private Page page;
	
	@Autowired
	private FileSaveHelper fileSaveHelper;
	@Resource(name="saveDir")
	private String saveDir;
	
	// root-context.xml에 선언되어있는 bean 객체가 int형이 두 개
	// 따라서 @Autowired로 하면 에러가 발생한다.
	@Resource(name="pageSize")
	private int pageSize;
	@Resource(name="pageBlock")
	private int pageBlock;


	@Transactional
	@Override
	public void list(String pageNum, Model model) {
		int totalCount = bbsDao.getTotalCount();
		HashMap<String, String> pagingMap = page.paging(Integer.parseInt(pageNum), totalCount, pageSize, pageBlock);
		model.addAttribute("pageCode", pagingMap.get("pageCode"));
		model.addAttribute("articleList", bbsDao.list(pagingMap));
		model.addAttribute("totalCount", totalCount);
	}
	
	
	@Override
	public void write(BBSDto article, List<MultipartFile> fname) {
	
		if(!fname.get(0).isEmpty()) {
			article.setFileStatus((byte)1);
		} 
	
		bbsDao.write(article);
		// query를 수행하면 값을 article을 리턴하지 않더라도
		// query가 수행되고 난 다음의 article 객체로 인식된다.
		// 따라서 article.getArticleNum()으로 찍으면
		// 0이 나오는 것이 아니고, 다음 글 번호가 나온다.
		// useGeneratedKeys
		//System.out.println(article.getArticleNum());
		if(!fname.get(0).isEmpty()) {	
			commonFileUpload(fname, article.getArticleNum());
		} 
	}

	
	@Override
	public String login(String id, String pass, HttpSession session) {
		String dbPass = bbsDao.login(id);
		String view = null;
		
		if(dbPass == null) {
			System.out.println("회원이 아닙니다");
			view = "joinMember";
		} else {
			if(pass.equals(dbPass)){
				//System.out.println("로그인 되었습니다");
				// session에 로그인 된 id를 심자
				session.setAttribute("id", id);
				// 그냥 list.jsp로 가면 controller를 거치지 않으므로
				// 화면에 출력할 값이 안 나온다.
				view = "redirect://list.bbs?pageNum=1";
			} else {
				//System.out.println("비밀번호가 틀렸습니다");
				view = "passFail";
			}
		}
		return view;
	}


/*	@Override
	public void content(int articleNum, int fileStatus, Model model) {
		BBSDto article = bbsDao.content(articleNum, fileStatus, model);
		
		//댓글 개수 가져오기
		article.setCommentCount(bbsDao.getCommentCount(articleNum));
		
		if( fileStatus == 1) {
			model.addAttribute("fileList", bbsDao.getFiles(articleNum));
		}
		model.addAttribute("article", article);
		
	}*/
	
	@Override
	public void content1(int articleNum, int fileStatus, Model model) {
		BBSDto article = bbsDao.content1(articleNum);	
		if( fileStatus == 1) {
			model.addAttribute("fileList", bbsDao.getFiles(articleNum));
		}
		model.addAttribute("article", article);
		
	}


	@Override
	public void reply(BBSDto article, List<MultipartFile> fname) {
			
		if(!fname.get(0).isEmpty()) {
			article.setFileStatus((byte)1);
		} 
		
		bbsDao.reply(article);
		
		if(!fname.get(0).isEmpty()) {	
			commonFileUpload(fname, article.getArticleNum());
		} 
	}


	@Override
	public void delete(String articleNum, int fileStatus) {
		
		List<String> deleteList = null;
		
		if(fileStatus == 1) {
			deleteList = bbsDao.getFileList(articleNum);
			for(String storedFname : deleteList) {
				File tempFile = new File(saveDir + storedFname);
				// 파일이 존재하는지 확인하는 메소드
				if(tempFile.exists()) {
					tempFile.delete();
				}
			}
		}
		
		bbsDao.delete(articleNum);
	}


	@Override
	public BBSDto updateGetArticle(String articleNum) {
		return bbsDao.updateGetArticle(articleNum);	
	}


	@Override
	public void update(BBSDto article) {
		bbsDao.update(article);
	}	
	
	
	public void commonFileUpload(List<MultipartFile> mFile, int articleNum) {
		FileDto fileDto = null;
		
		for(MultipartFile uploadFile : mFile) {
			if( !uploadFile.isEmpty() ) {
				String storedFname = fileSaveHelper.save(uploadFile);
				
				fileDto = new FileDto();
				fileDto.setOriginFname(uploadFile.getOriginalFilename());
				fileDto.setStoredFname(storedFname);
				fileDto.setFileLength(uploadFile.getSize());
				fileDto.setArticleNum(articleNum);				
				bbsDao.insertFile(fileDto);
			}
		}
	}


	@Override
	public FileSystemResource download(HttpServletResponse resp, String storedFname, String originFname,
			int fileLength) {
			resp.setContentType("application/download");
			resp.setContentLength(fileLength);
			try {
				originFname = URLEncoder.encode(originFname, "utf-8").replace("+", "%20").replaceAll("%28", "(").replaceAll("%29", ")");
			} catch (Exception e) {
				
			}
			
			resp.setHeader("Content-Disposition", "attachment;" + " filename=\"" + originFname + "\";");
			FileSystemResource fsr = new FileSystemResource(saveDir + storedFname);
			return fsr;
	}
	
	
}