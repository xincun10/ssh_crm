package com.zcy.dao;

import java.util.List;

import com.zcy.entity.Visit;

public interface VisitDao {

	void addVisit(Visit visit);

	List<Visit> findAll();

	Visit findOne(int vid);

	void update(Visit visit);

}
