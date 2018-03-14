package com.yh.manager.commodity.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerCommodityController {
	
	@RequestMapping(value="commodity.html",method=RequestMethod.GET)
	private String orderList(ModelMap map) {
		return "ad/orderlist/list";
	}
	//addcommodity.html
	@RequestMapping(value="addcommodity.html",method=RequestMethod.GET)
	private String addCommodity(ModelMap map) {
		return "manager/addcommodity";
	}
}
