package com.wind.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "notice/home";
	}
	
	@RequestMapping("/login.html")
	public String login() {
		return "security/login";
	}
	
	@RequestMapping("/welcome.html")
	public String welcome() {
		return "security/welcome";
	}
	
	@RequestMapping("/loginForm.html")
	public String loginForm() {
		return "security/loginForm";
	}
	
	@RequestMapping("/notice/home")
	public String notice_home() {
		return "notice/home";
	}
	
	
}
