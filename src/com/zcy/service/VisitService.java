package com.zcy.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zcy.dao.VisitDao;
import com.zcy.entity.Visit;

@Transactional
public class VisitService {

	private VisitDao visitDao;
	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}
	
	public void addVisit(Visit visit) {
		visitDao.addVisit(visit);
	}

	public List<Visit> findAll() {
		// TODO Auto-generated method stub
		return visitDao.findAll();
	}

	public Visit findOne(int vid) {
		// TODO Auto-generated method stub
		return visitDao.findOne(vid);
	}

	public void update(Visit visit) {
		// TODO Auto-generated method stub
		visitDao.update(visit);
	}
	
}
