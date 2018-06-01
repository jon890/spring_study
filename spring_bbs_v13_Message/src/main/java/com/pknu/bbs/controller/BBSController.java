package com.pknu.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pknu.bbs.dto.BBSDto;
import com.pknu.bbs.dto.UpdateDto;
import com.pknu.bbs.service.BBSService;

@Controller
public class BBSController {
	@Autowired
	BBSService bbsService;	
	
//	@ModelAttribute는 파라미터 이름을 반드시 적어주자
//	그러면 객체가 아니고 프리미티브 타입이 와도 됨
//	public String list(@ModelAttribute("pageNum") String pageNum, Model model){
//	public String list(@ModelAttribute("pageNum") int pageNum, Model model){
//	아래코드는 에러가 남...파라미터 이름을 생략하면 int를 객체로 생성할려고 해서 에러가남
//	public String list(@ModelAttribute int pageNum, Model model){
//	아래코드는 객체를 생성할려고 하는데 String이니까 에러는 안나지만..파라미터가 안넘어옴
//	public String list(@ModelAttribute S`tring pageNum, Model model){
	@Transactional
	@RequestMapping("/list.bbs")
	public String list(@ModelAttribute("pageNum") int pageNum, Model model, HttpSession session){	
		model=bbsService.list(pageNum,model);
		String id = (String)session.getAttribute("id");
		if(id !=null){
			model.addAttribute("messageCount", bbsService.getMessageCount(id));
		}
		return "bbs/list";		
	}	
	
	@Transactional
	@RequestMapping(value="/content.bbs")
	public String content(@RequestParam int articleNum,@ModelAttribute("pageNum") String pageNum,
						  @RequestParam int fileStatus,	Model model, HttpSession session){
						
		bbsService.getArticle(articleNum, fileStatus ,model);		
				
		return "bbs/content";
	}
	
	@RequestMapping(value="/writeForm.bbs",method=RequestMethod.GET)
	public String writeForm(HttpSession session){	
		return "bbs/writeForm";
	}
	
	@Transactional
	@RequestMapping(value="/write.bbs",method=RequestMethod.POST)
	public String write(BBSDto article,HttpSession session){		
		article.setId((String)session.getAttribute("id"));
		bbsService.insertArticle(article);				
		return "redirect:/list.bbs?pageNum=1";
	}
		
	@RequestMapping(value="/login.bbs", method=RequestMethod.POST)
	public String  login(String id, String pass, HttpSession session){		
		return bbsService.login(id,pass,session);
	}
	
	@RequestMapping(value="/login.bbs", method=RequestMethod.GET)
	public String  loginForm(){		
		return "login/login";
	}
	
	@RequestMapping(value="/logout.bbs")
	public String  logout(HttpSession session,HttpServletRequest req){		
		session.invalidate();
		return "redirect:/list.bbs?pageNum=1";
	}
	
	@RequestMapping(value="/join.bbs")
	public String  join(){		
		return "join/joinMember";
	}
	
//	@ResponseBody
//	@RequestMapping(value="/joinIdCheck.bbs")
//	public int joinIdCheck(String inputId){
//		int idStatus=bbsService.joinIdCheck(inputId);
//		return idStatus;
//	}
	
	
	@RequestMapping(value="/replyForm.bbs")
	public String replyForm(@ModelAttribute("pageNum") String pageNum,BBSDto article, Model model){
		model.addAttribute("depth",article.getDepth());	
		model.addAttribute("groupId",article.getGroupId());		
		return "bbs/replyForm";
	}
	
	@RequestMapping(value="/reply.bbs")
	public String reply(BBSDto article,HttpSession session, 
						@RequestParam String pageNum, MultipartFile springFname){		
		article.setId((String)session.getAttribute("id"));
		bbsService.replyArticle(article,springFname);				
		return "redirect:/list.bbs?pageNum="+pageNum;
	}
	
	@RequestMapping(value="/updateForm.bbs")
	public String updateGetArticle(@ModelAttribute("articleNum") int articleNum,
			                       @ModelAttribute("pageNum") String pageNum,
			                       @ModelAttribute("fileStatus") int fileStatus,
							       Model model){		
		bbsService.updateGetArticle(articleNum, fileStatus, model);				
		return "bbs/updateForm";
	}
	
	@RequestMapping(value="/update.bbs", method=RequestMethod.POST)
	public String update(BBSDto article,
//			아래와 같이 사용시에는 오류가 남
//			ArrayList<String> storedFnameList, ..set,get 메소드가 없음
//			UpdateDto를 만들어서 set,get 메소드를 사용해야지
//			jsp에서 복수개의 name 속성을 가지는 파라미터를 받을 수 있음 
			UpdateDto updateDto,
			MultipartHttpServletRequest mRequest, 
			String pageNum, Model model, int fileCount){	
		System.out.println(updateDto);
		bbsService.updateArticle(article,updateDto,mRequest, model,fileCount);					
		return "redirect:/content.bbs?pageNum="+pageNum;
	}
	 
	@RequestMapping(value="/delete.bbs")
	public String delete(@RequestParam int articleNum,@RequestParam String pageNum,
						 @RequestParam("fileStatus") int fileStatus){		
		bbsService.delete(articleNum,fileStatus);				
		return "redirect:/list.bbs?pageNum="+pageNum;
	}
	
//	@RequestMapping(value="/download.bbs", produces="application/download")
	@RequestMapping(value="/download.bbs")
	@ResponseBody
	public FileSystemResource download(HttpServletResponse resp,
										@RequestParam("storedFname") String storedFname){	
		return bbsService.download(resp, storedFname);
	}
	

	


}
