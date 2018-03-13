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
	
	   
}
