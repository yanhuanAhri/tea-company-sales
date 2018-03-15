package com.yh.manager.commodity.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.commodity.service.CommodityService;
import com.yh.entity.Commodity;

@Controller
@Configuration
@RequestMapping("/manager")
public class ManagerCommodityController {
	 @Autowired
	private CommodityService commodityService;
		
	
	@RequestMapping(value="commodity.html",method=RequestMethod.GET)
	private String orderList(ModelMap map) {
		return "ad/orderlist/list";
	}
	 @GetMapping(value="/addcommodity.html")
		private String addCommodity(ModelMap map) {
			return "manager/addcommodity";
		}
	
	@PostMapping("/saveCommodity")
	@ResponseBody
	public void save(@ModelAttribute Commodity commodity,
			HttpServletResponse response ) {
		commodityService.saveCommodity(commodity);
	}
}
