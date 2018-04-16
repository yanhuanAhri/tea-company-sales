package com.yh.manager.area.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.entity.Area;
import com.yh.manager.area.service.AreaService;

@Controller
@Configuration
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	@GetMapping("getArea")
	@ResponseBody
	public List<Area> getArea(@RequestParam(name="parentId",required = false)Long parentId,
			Model model,HttpServletResponse response){
		return areaService.findBy(parentId);
	}
	//
}
