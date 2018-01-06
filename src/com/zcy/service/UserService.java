package com.zcy.service;

import org.springframework.transaction.annotation.Transactional;

import com.zcy.dao.UserDao;
import com.zcy.entity.User;

@Transactional
public class UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//µÇÂ¼µÄ·½·¨
	public User login(User user) {
		return userDao.loginUser(user);
	}
	
}
