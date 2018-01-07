package com.zcy.dao;

import java.util.List;

import com.zcy.entity.User;

public interface UserDao {

	User loginUser(User user);

	List<User> findAll();

}
