package com.zcy.dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zcy.entity.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	//�����ϵ�˵ķ���
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}

}
