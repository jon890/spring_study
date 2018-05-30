package com.ict.mongo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ict.mongo.dto.UserDto;
import com.ict.mongo.service.MongoService;

@Controller
public class MongoController {

	@Autowired
	private MongoService mongoService;
	
	@RequestMapping("main.mongo")
	public String main(Model model) {
			model.addAttribute("userList", mongoService.getUserList());
		return "main";
	}
	
	
	@RequestMapping(value = "insert.mongo" , method=RequestMethod.GET)
	public String insertForm() {
		return "insert";
	}
	
	
	@RequestMapping(value = "insert.mongo" , method=RequestMethod.POST)
	public String insert(@RequestParam Map<String, String> map) {
		System.out.println("매개변수 정보 " + map);
		mongoService.insert(map);
		return "redirect:/main.mongo";
	}
	
	@RequestMapping(value = "detail.mongo" , method=RequestMethod.GET)
	public String detail(UserDto userDto, Model model) {
		model.addAttribute("user", mongoService.getUser(userDto));
		return "detail";
	}
	
	@RequestMapping(value = "modify.mongo", method=RequestMethod.POST)
	@ResponseBody
	public UserDto modify(@RequestParam Map<String, String> map) {
		return mongoService.changeUser(map);
//		return "redirect:/detail.mongo?=" + map.get("id");
//		return "redirect:/main.mongo";
	}
	
	
	@RequestMapping(value = "delete.mongo", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> delete(@RequestParam String id) {
		HashMap<String, String> hm = new HashMap<String, String>();
		
		try {
			mongoService.removeUser(id);
			hm.put("code", "1");
		} catch(Exception e){
			hm.put("code", "0");
		}
		
		return hm;
	}
	
	
	
}