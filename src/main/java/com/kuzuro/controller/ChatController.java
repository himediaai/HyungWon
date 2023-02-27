package com.kuzuro.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	

	@RequestMapping(value = "/chatting/login2", method = RequestMethod.GET)
	public String login2() {
		return "login2";
	}
	
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public String loginProcess(@RequestParam String id, HttpServletRequest request) {
		
		logger.info("Welcome "+id);
		
    	HttpSession session = request.getSession();
    	session.setAttribute("id", id);
		return "chat";
	}
	
}
