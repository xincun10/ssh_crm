package com.zcy.service;

import java.util.List;

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

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	
}
