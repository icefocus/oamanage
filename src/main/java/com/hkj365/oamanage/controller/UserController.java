package com.hkj365.oamanage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hkj365.oamanage.pojo.User;
import com.hkj365.oamanage.service.IUserService;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	final static Logger logger = LoggerFactory.getLogger(UserController.class);
	private String ll="just ll";
	@RequestMapping(value="/showUser", method=GET)
	public String toIndex(HttpServletRequest request,Model model){
		logger.warn("Entry the UserController."+ ll);
		logger.info(ll);
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}
}
