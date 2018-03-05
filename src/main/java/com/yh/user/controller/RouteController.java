package com.yh.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yh.user.service.UserService;

@Controller
public class RouteController {

  @Autowired
  private UserService userService;
  
  @RequestMapping({"/", "index"})
  public String index(Model model) {
    model.addAttribute("user", userService.findByUserName("userName"));
    return "index";
  }
  
}
