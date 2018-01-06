package com.zcy.dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zcy.entity.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	//添加联系人的方法
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}

}
