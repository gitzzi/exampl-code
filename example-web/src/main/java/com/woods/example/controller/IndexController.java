package com.woods.example.controller;

import com.woods.example.common.utils.PageHelper;
import com.woods.example.model.User;
import com.woods.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
		System.out.println(user.getName());
		return "index";

	}

	@RequestMapping( value = "findPage")
	public String findPage(){

		int pageNumber = 1;
		int pageSize = 2;

		List<String> ns = new ArrayList<String>();
		ns.add("2");
		ns.add("5");

		User user = new User();
		user.setNames(new String[]{"2","5"});
		user.setNs(ns);

		List<User> us = userService.findByUser(user);
		for (User u : us){
			System.out.println(u.toString() + "---------");
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		PageHelper.Page<User> page = userService.findByPage(user, pageNumber, pageSize);
		for (User u : page.getResult()){
			System.out.println(u.toString() + "============");
		}
		System.out.println(page.toString());
		return "index";

	}
	@RequestMapping( value = "getuser")
	public String getByUser(){
		User user = new User();
		user.setName("2");
		List<User> userList = userService.getByUser(user);
		System.out.println("size: " + userList.size());
		for (User u : userList){
			System.out.println(u.toString());
		}
		return "index";

	}

}
