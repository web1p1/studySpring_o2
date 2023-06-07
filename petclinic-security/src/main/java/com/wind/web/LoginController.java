package com.wind.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
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

}
