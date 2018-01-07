package com.zcy.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zcy.entity.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

	//添加一条拜访记录
	public void addVisit(Visit visit) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(visit);
	}

	//显示拜访记录列表
	public List<Visit> findAll() {
		return (List<Visit>) this.getHibernateTemplate().find("from Visit");
	}

	//根据vid查找拜访记录
	public Visit findOne(int vid) {
		return this.getHibernateTemplate().get(Visit.class, vid);
	}

	//修改访问记录
	public void update(Visit visit) {
		this.getHibernateTemplate().update(visit);
	}

	
}
