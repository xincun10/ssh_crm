package com.zcy.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.zcy.entity.User;

public class UserDaoImpl implements UserDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//��¼�ķ���
	@SuppressWarnings("all")
	public User loginUser(User user) {
		//��¼�Ĳ�ѯ����
		//�����û����������ѯ
		List<User> list = (List<User>) hibernateTemplate.
					find("from User where username=? and password=?", user.getUsername(), user.getPassword());
		//����user����
		if(list != null && list.size()!=0)
		{
			User u = list.get(0);
			return u;
		}
		return null;
	}

	//��ѯ�����û�
	public List<User> findAll() {
		return (List<User>) this.hibernateTemplate.find("from User");
	}
	
}
