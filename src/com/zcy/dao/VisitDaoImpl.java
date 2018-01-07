package com.zcy.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zcy.entity.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

	//���һ���ݷü�¼
	public void addVisit(Visit visit) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(visit);
	}

	//��ʾ�ݷü�¼�б�
	public List<Visit> findAll() {
		return (List<Visit>) this.getHibernateTemplate().find("from Visit");
	}

	//����vid���Ұݷü�¼
	public Visit findOne(int vid) {
		return this.getHibernateTemplate().get(Visit.class, vid);
	}

	//�޸ķ��ʼ�¼
	public void update(Visit visit) {
		this.getHibernateTemplate().update(visit);
	}

	
}
