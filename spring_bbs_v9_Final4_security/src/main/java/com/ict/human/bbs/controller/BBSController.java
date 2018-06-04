package com.ict.human.bbs.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ict.human.bbs.dto.BBSDto;
import com.ict.human.bbs.service.BBSService;

@Controller
public class BBSController {

//	DI 시켜줄 의존객체를 못 찾으면 null 상태가 되고 WAS 구동시에 에러가 나지 않는다.
//	@Autowired(required=false)
	
	@Autowired
	@Qualifier("a")
	private BBSService bbsService;
	
	@Resource(name = "saveDir")
	private String saveDir;

	@RequestMapping(value = "/list.bbs", method = RequestMethod.GET)
	public String list(@ModelAttribute("pageNum") String pageNum, Model model) {
		bbsService.list(pageNum, model);
		return "list";
	}

	// 글쓰기 버튼을 눌렀을 때 발생하는 동작
	@RequestMapping(value = "/write.bbs", method = RequestMethod.GET)
	public String writeForm(HttpSession session) {
		return "writeForm";
	}

	@RequestMapping(value = "/write.bbs", method = RequestMethod.POST)
	// name과 연결되있는 속성값들이 BBSDto로 자동으로 연결된다. (spring 기능)
	public String write(BBSDto article, HttpSession session) {

		// 세션에서 id를 받아온다 -> object로 넘어오기 때문에 downcasting 필요함
		article.setId((String) session.getAttribute("id"));
		// System.out.println("파일 이름 출력" + files);
		bbsService.write(article);

		// secure coding -> 여러번 submit 방지를 위해서 redirect 사용
		return "redirect:/list.bbs?pageNum=1";
	}
	

	@RequestMapping(value = "/login.bbs", method = RequestMethod.POST)
	public String login(@RequestParam String id, String pass, HttpSession session) {
		System.out.println("공백제거id//" + id);
		System.out.println("공백제거pwd//" + pass);
		return bbsService.login(id, pass, session);
	}

	@RequestMapping(value = "/login.bbs", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/content.bbs", method = RequestMethod.GET)
	public String content(@RequestParam("articleNum") String articleNum, 
						  @ModelAttribute("pageNum") String pageNum,
						  @RequestParam("fileStatus") int fileStatus, 
						  Model model, 
						  HttpSession session, 
						  HttpServletResponse resp) {
		// 상관관계 하위질의를 이용해서 댓글 개수를 하나의 쿼리문으로 실행
		bbsService.content1(articleNum, fileStatus, model);
		// 해당글의 댓글 수를 가져오는 쿼리를 한 번더 실행
		// bbsService.content(articleNum, fileStatus, model);
		return "content";
	}

	@RequestMapping(value = "/replyForm.bbs", method = RequestMethod.POST)
	public String replyForm(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("depth") int depth,
			@ModelAttribute("groupId") int groupId, Model model, HttpSession session) {
		return "replyForm";
	}

	@RequestMapping(value = "/reply.bbs", method = RequestMethod.POST)
	public String reply(@RequestParam("pageNum") String pageNum, BBSDto article, HttpSession session) {
		article.setId((String) session.getAttribute("id"));
		bbsService.reply(article);
		return "redirect:/list.bbs?pageNum=".concat(pageNum);
	}

	@RequestMapping(value = "/delete.bbs", method = RequestMethod.GET)
	public String delete(@RequestParam("pageNum") String pageNum, @RequestParam("articleNum") String articleNum,
			@RequestParam("fileStatus") int fileStatus) {

		bbsService.delete(articleNum, fileStatus);
		return "redirect:/list.bbs?pageNum=".concat(pageNum);
	}

	
	// 수정할 글 읽어오기
	@RequestMapping(value = "/update.bbs", method = RequestMethod.GET)
	public String getUpdateArticle(@ModelAttribute("articleNum") String articleNum,
								   @ModelAttribute("pageNum") String pageNum,
								   @ModelAttribute("fileStatus") int fileStatus,
								   Model model) {

		model.addAttribute("article", bbsService.getUpdateArticle(articleNum));
		if(fileStatus == 1) {
			model.addAttribute("files", bbsService.getFiles(articleNum));
			model.addAttribute("fileCount", bbsService.getFiles(articleNum).size());
		} else {
			model.addAttribute("fileCount", 0);
		}
		return "updateForm";
	}

	// 글 수정하기 버튼을 눌렀을 때 동작
	@RequestMapping(value = "/update.bbs", method = RequestMethod.POST)
	public String update(BBSDto article,
						 @RequestParam("articleNum") String articleNum, 
						 @RequestParam("pageNum") String pageNum,
						 String[] deleteFileName,
						 Model model,
						 int fileCount) {
		bbsService.update(article, deleteFileName, model, fileCount);
		//System.out.println("article status : " + article.getFileStatus());
		return "redirect:/content.bbs?pageNum=" + pageNum + "&articleNum=" + articleNum + "&fileStatus=" + article.getFileStatus();
	}

	
	
	@RequestMapping(value = "/logout.bbs")
	public String logout(HttpSession session) {
		// 세션을 끊으려면 invalidate() 메소드를 이용한다.
		session.invalidate();
		return "redirect:/list.bbs?pageNum=1";
	}
	
	@RequestMapping(value = "/joinForm.bbs")
	public void joinForm() {
	}
	// 리턴값이 보이드이면 요청이름이 뷰 이름과 동일하다
	
	
	@RequestMapping(value = "/joinIdCheck.bbs")
	@ResponseBody
	public String joinIdCheck(@RequestParam String inputId) {
		return bbsService.joinIdCheck(inputId);
	}

}