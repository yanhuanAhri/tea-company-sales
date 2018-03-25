package com.yh.sales.order.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
@Controller
public class OrderController {
	
	@GetMapping("pay.html")
	public String GoPay(Model model) {
		return "sales/pay";
	}
}
