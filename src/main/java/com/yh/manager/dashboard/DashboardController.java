package com.yh.manager.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@Controller
@RequestMapping("/manager")
///manager/dashboard.html 
public class DashboardController {
	private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);

	@RequestMapping(value = "dashboard.html", method = RequestMethod.GET)
	public String goProfile(ModelMap map) {
		return "manager/dashboard/test";
	}
}
