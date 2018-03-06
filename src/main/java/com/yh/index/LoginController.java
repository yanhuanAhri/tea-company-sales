package com.yh.index;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Configuration
public class LoginController {
	
	@RequestMapping(value="login.html",method=RequestMethod.GET)
	private String login(Model map) {
		return "login";
	}

}
