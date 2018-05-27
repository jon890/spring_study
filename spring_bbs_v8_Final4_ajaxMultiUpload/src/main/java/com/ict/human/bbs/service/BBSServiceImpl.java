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
	public void write(BBSDto article) {
		
		bbsDao.write(article);
		
		if( article.getFileStatus() == 1 ) {
			commonFileUpload(article.getFiles(), article.getArticleNum());
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
			model.addAttribute("files", bbsDao.getFiles(articleNum));
		}
		model.addAttribute("article", article);
		
	}


	@Override
	public void reply(BBSDto article) {
	
		bbsDao.reply(article);
		if( article.getFileStatus() == 1 ) {
			commonFileUpload(article.getFiles(), article.getArticleNum());
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
	
	@Override
	public List<String> getFiles(String articleNum) {
		return bbsDao.getFileList(articleNum);
	}

	@Override
	public void commonFileUpload(List<String> files, int articleNum) {
		
		FileDto fileDto = null;
		for(String file : files) {
			fileDto = new FileDto();
			fileDto.setArticleNum(articleNum);
			fileDto.setStoredFname(file);
			bbsDao.insertFile(fileDto);
		}
		
	}
	
	@Override
	// 파일시스템 리소스 이용
	public FileSystemResource download(HttpServletResponse resp, String storedFname) {
			resp.setContentType("application/download");
			int idx = storedFname.indexOf("_") + 1;
			String originFname = storedFname.substring(idx);
			
			try {
				originFname = URLEncoder.encode(originFname, "utf-8").replace("+", "%20").replaceAll("%28", "(").replaceAll("%29", ")");
			} catch (Exception e) {	
			}
			
			resp.setHeader("Content-Disposition", "attachment;" + " filename=\"" + originFname + "\";");
			System.out.println(saveDir + storedFname);
			FileSystemResource fsr = new FileSystemResource(saveDir + storedFname);
			return fsr;
	}
	
}