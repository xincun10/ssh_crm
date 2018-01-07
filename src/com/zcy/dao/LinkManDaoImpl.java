package com.zcy.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zcy.entity.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	//添加联系人的方法
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}

	//显示联系人列表
	public List<LinkMan> list() {
		return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
	}

	//根据id查询联系人
	public LinkMan findOne(int linkid) {
		return this.getHibernateTemplate().get(LinkMan.class, linkid);
	}

	//更新联系人值
	public void updateLinkMan(LinkMan linkman) {
		this.getHibernateTemplate().update(linkman);
	}

}
