package com.hkj365.oamanage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkj365.oamanage.dao.IUserDao;
import com.hkj365.oamanage.pojo.User;
import com.hkj365.oamanage.service.IUserService;

@Service("userService")

public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	//@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}

}

/*
public class UserServiceImpl{
	@Resource
	private IUserDao userDao;
	//@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}

}
*/