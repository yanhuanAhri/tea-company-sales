package com.yh.sales.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yh.entity.User;
import com.yh.sales.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

 /* @RequestMapping(value = "/addUser", method = RequestMethod.GET)
  public boolean addUser(@RequestParam("loginname") String loginname,
      @RequestParam("password") String password) {
    return userService.addUser(loginname, password);
  }*/


/*  @RequestMapping(value = "/addUserWithBackId", method = RequestMethod.GET)
  public User addUserWithBackId(@RequestParam("loginname") String loginname,
      @RequestParam("password") String password) {
    return userService.addUserWithBackId(loginname, password);
  }*/

  @ResponseBody
  @RequestMapping(value = "getUser", method = RequestMethod.GET)
  public User findByUserName() {
    return userService.findByUserName("zhangsan");
  }

}
