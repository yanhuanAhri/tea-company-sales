package com.yh.manager.commodity.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Configuration
@RequestMapping("/manager")
public class ManagerCommodityController {
	
	@RequestMapping(value="commodity.html",method=RequestMethod.GET)
	private String orderList(ModelMap map) {
		return "ad/orderlist/list";
	}
	 @GetMapping(value="/addcommodity.html")
		private String addCommodity(ModelMap map) {
			return "manager/addcommodity";
		}
}
