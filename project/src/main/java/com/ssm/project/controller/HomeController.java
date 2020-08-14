package com.ssm.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String home() {
		log.debug("home");
		return "redirect:/stand.do";
	}
	
	@RequestMapping("stand.do")
	public String stand() {
		log.debug("stand");
		return "stand";
	}
	
	@RequestMapping("entrance.do")
	public String entrance() {
		log.debug("entrance.do");
		return "entrance";
	}
	
}
