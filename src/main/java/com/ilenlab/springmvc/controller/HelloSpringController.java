package com.ilenlab.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilenlab.springmvc.model.Company;
@Controller
public class HelloSpringController {
	@RequestMapping("/")
	public String Home(Model model) {
		model.addAttribute("welcome", "*** Welcome to my home page ***");
		return "home";
	}
	
	@RequestMapping("/hello")
	public String Hello(Model model) {
		model.addAttribute("greeting", "Hello Spring MVC");
		return "hellospring";
	}
	
	@RequestMapping("/user")
	public String userInfo(Model model, @RequestParam(value="name", defaultValue="Guest") String name) {
		model.addAttribute("name", name);
		if("admin".equals(name)) {
			model.addAttribute("email", "admin@example.com");
		} else {
			model.addAttribute("email", "not set");
		}
		
		return "userinfo";
	}
	
	@RequestMapping("/web/fe/{sitePrefix}/{language}/document/{id}/{naturalText}")
	public String documentView(Model model, 
			@PathVariable(value="sitePrefix") String sitePrefix,
			@PathVariable(value="language") String language,
			@PathVariable(value="id") Long id,
			@PathVariable(value="naturalText") String naturalText) {
		model.addAttribute("sitePrefix", sitePrefix);
		model.addAttribute("language", language);
		model.addAttribute("id", id);
		model.addAttribute("naturalText", naturalText);
		
		String documentName = "Java tutorial for Beginers";
		if(id == 1900) {
			documentName = "Spring MVC for beginer";
		}
		
		model.addAttribute("documentName", documentName);
		return "documentView";
	}
	
	@RequestMapping(value="/saveResult")
	@ResponseBody
	public String authorInfo(Model model) {
		return "saved";
	}
	
	@RequestMapping(value="{name}", method = RequestMethod.GET)
	@ResponseBody
	public Company getCompanyInJSON(Model model, @PathVariable(value="name") String name) {
		Company com = new Company();
		com.setName(name);
		com.setStaffName(new String[]{"staff1", "staff2", "staff3"});
		return com;
	}
	
}
