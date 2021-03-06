package com.ict.human.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ict.human.bbs.dto.BBSDto;
import com.ict.human.bbs.service.BBSService;

@Controller
public class BBSController {
	
	
	@Autowired
	private BBSService bbsService;
	
	
	//	@ModelAttribute는 파라미터 이름을 반드시 적어주자
	//	그러면 객체가 아니고 프리미티브 타입이 와도 됨
	//	public String list(@ModelAttribute("pageNum") String pageNum, Model model){
	//	public String list(@ModelAttribute("pageNum") int pageNum, Model model){
	//	아래코드는 에러가 남...파라미터 이름을 생략하면 int를 객체로 생성할려고 해서 에러가남
	//	public String list(@ModelAttribute int pageNum, Model model){
	//	아래코드는 객체를 생성할려고 하는데 String이니까 에러는 안나지만..파라미터가 안넘어옴
	//	public String list(@ModelAttribute String pageNum, Model model){

	@RequestMapping(value = "/list.bbs", method = RequestMethod.GET)
	public String list(@ModelAttribute("pageNum") String pageNum, Model model) {
		bbsService.list(pageNum, model);
		return "list";
	}
	
	
	// 글쓰기 버튼을 눌렀을 때 발생하는 동작
	@RequestMapping(value = "/write.bbs", method = RequestMethod.GET)
	public String writeForm(HttpSession session,
							HttpServletResponse resp) {
		return "writeForm";
	}
	
	
	@RequestMapping(value = "/write.bbs", method = RequestMethod.POST)
	// name과 연결되있는 속성값들이 BBSDto로 자동으로 연결된다. (spring 기능)
	public String write(BBSDto article, HttpSession session,
						@RequestPart("fname") List<MultipartFile> fname) {
		
		// 세션에서 id를 받아온다 -> object로 넘어오기 때문에 downcasting 필요함
		article.setId((String)session.getAttribute("id"));		
		
		bbsService.write(article, fname);

		// secure coding -> 여러번 submit 방지를 위해서 redirect 사용
		return "redirect:/list.bbs?pageNum=1";
	}
	
	
	@RequestMapping(value = "/login.bbs", method = RequestMethod.POST)
	public String login(@RequestParam String id, String pass, HttpSession session) {
		return bbsService.login(id, pass, session);
	}
	
	@RequestMapping(value = "/login.bbs", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	
	@RequestMapping(value = "/content.bbs", method = RequestMethod.GET)
	public String content(@RequestParam("articleNum") int articleNum,
						  @ModelAttribute("pageNum") String pageNum,
						  @RequestParam("fileStatus") int fileStatus,
						  Model model,
						  HttpSession session, HttpServletResponse resp) {
		//상관관계 하위질의를 이용해서 댓글 개수를 하나의 쿼리문으로 실행
		bbsService.content1(articleNum, fileStatus, model);
		// 해당글의 댓글 수를 가져오는 쿼리를 한 번더 실행
		//bbsService.content(articleNum, fileStatus, model);
		return "content";
	}
	
	
	@RequestMapping(value = "/replyForm.bbs", method = RequestMethod.POST)
	public String replyForm(@ModelAttribute("pageNum") String pageNum,
							@ModelAttribute("depth") int depth,
							@ModelAttribute("groupId") int groupId,
							Model model, HttpSession session) {
			return "replyForm";
	}
	
	
	
	@RequestMapping(value = "/reply.bbs", method = RequestMethod.POST)
	public String reply(@RequestParam("pageNum") String pageNum,
					    @RequestPart("fname") List<MultipartFile> fname,
					    BBSDto article, HttpSession session) {
		article.setId((String)session.getAttribute("id"));		
		bbsService.reply(article, fname);
		return "redirect:/list.bbs?pageNum=".concat(pageNum);
	}
	
	
	
	@RequestMapping(value = "/delete.bbs", method = RequestMethod.GET)
	public String delete(@RequestParam("pageNum") String pageNum,
						 @RequestParam("articleNum") String articleNum,
						 @RequestParam("fileStatus") int fileStatus) {
							
		bbsService.delete(articleNum, fileStatus);
		return "redirect:/list.bbs?pageNum=".concat(pageNum);
	}
	
	
	
	@RequestMapping(value = "/update.bbs", method = RequestMethod.GET)
	public String updateGetArticle(@ModelAttribute("articleNum") String articleNum,
								   @ModelAttribute("pageNum") String pageNum, Model model) {
		
		model.addAttribute("article", bbsService.updateGetArticle(articleNum));
		return "updateForm";
	}
	
	
	
	@RequestMapping(value = "/update.bbs", method = RequestMethod.POST)
	public String update(@RequestParam("articleNum") String articleNum,
						 @RequestParam("pageNum") String pageNum,
						 BBSDto article) {
		bbsService.update(article);
		return "redirect:/content.bbs?pageNum="+pageNum+"&articleNum="+articleNum+"&fileStatus="+article.getFileStatus();
	}
	
	
	@RequestMapping(value = "/logout.bbs")
	public String logout(HttpSession session){
		// 세션을 끊으려면 invalidate() 메소드를 이용한다.
		session.invalidate();
		return "redirect:/list.bbs?pageNum=1";
	}
	
	
	
	@RequestMapping(value = "/download.bbs")
	@ResponseBody
	public FileSystemResource download(HttpServletResponse resp,
									   @RequestParam String storedFname,
									   @RequestParam String originFname,
									   @RequestParam int fileLength) {
		return bbsService.download(resp, storedFname, originFname, fileLength);
		
	}
	
}