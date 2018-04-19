package com.yh.sales.comment.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Configuration
public class CommentController {
	
	@GetMapping("commentlist.html")
	public String goOrderinfo(@RequestParam("orderNum")String orderNum,Model model) {
		model.addAttribute("orderNum", orderNum);
		return "person/commentlist";
	}

}
