package com.woods.example.controller;

import com.woods.example.model.User;
import com.woods.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/index")
public class IndexController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "hello")
	public String helloWorld(){

		System.out.println("ccccccccccccccccc");

		return "index";
	}


	@RequestMapping( value = "finduser")
	public String findUserByid(){
		User user = userService.findUserById(1);
		StringUtils.isEmpty("ss");
		System.out.println(user.getName());
		return "index";

	}

}
