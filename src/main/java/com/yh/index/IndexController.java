package com.yh.index;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Configuration
public class IndexController {
	
	 @GetMapping("/")
     public String goIndex() {
        return "home";
     }
	 @GetMapping("home.html")
	 public String goHome() {
        return "home";
	 }
	
	   
}
