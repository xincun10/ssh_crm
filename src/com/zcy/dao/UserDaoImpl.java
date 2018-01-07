package com.zcy.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.zcy.entity.User;

public class UserDaoImpl implements UserDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//登录的方法
	@SuppressWarnings("all")
	public User loginUser(User user) {
		//登录的查询操作
		//根据用户名和密码查询
		List<User> list = (List<User>) hibernateTemplate.
					find("from User where username=? and password=?", user.getUsername(), user.getPassword());
		//返回user对象
		if(list != null && list.size()!=0)
		{
			User u = list.get(0);
			return u;
		}
		return null;
	}

	//查询所有用户
	public List<User> findAll() {
		return (List<User>) this.hibernateTemplate.find("from User");
	}
	
}
