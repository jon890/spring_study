package com.ict.mongo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.mongo.service.MongoService;

@Controller
public class MongoController {

	@Autowired
	private MongoService mongoService;
	
	@RequestMapping("/main.mongo")
	public String main(Model model) {
			//model.addAttribute("userList", mongoService.getUserList());
		return "main";
	}
	
	
	@RequestMapping(value = "/insert.mongo" , method=RequestMethod.GET)
	public String insertForm() {
		return "insert";
	}
	
	
	@RequestMapping(value = "/insert.mongo" , method=RequestMethod.POST)
	public String insert(@RequestParam Map<String, String> map) {
		System.out.println("매개변수 정보 " + map);
		mongoService.insert(map);
		return "redirect:/main.mongo";
	}
	
}