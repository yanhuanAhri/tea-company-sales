package com.yh.manager.index;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Configuration
//@RequestMapping("/manager")
public class ManagerIndexController {
	
	 @GetMapping("/manager")
     public String goIndex() {
        return "manager/index";
     }
	 
	 @GetMapping("/manager/head.html")
     public String goHead() {
        return "manager/head";
     }
	 
	 @GetMapping("/manager/left.html")
     public String goLeft() {
        return "manager/left";
     }
	 
	 @GetMapping("/manager/main.html")
     public String goMain() {
        return "manager/main";
     }
	 
	 @GetMapping("/manager/home.html")
     public String goHome() {
        return "manager/home";
     }
	
	   
}
