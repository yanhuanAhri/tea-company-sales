package com.yh.sales.comment.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.sales.comment.service.CommentService;

@Controller
@Configuration
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("commentlist.html")
	public String goOrderinfo(@RequestParam("orderNum")String orderNum,Model model) {
		model.addAttribute("orderNum", orderNum);
		return "person/commentlist";
	}
	
	@GetMapping("getComment")
	@ResponseBody
	public Map<String,Object> getComment(@RequestParam("commodityNum")String commodityNum,
			HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=commentService.findByCommodityNum(commodityNum);
		map.put("code", 1);
		return map;
	}
}
