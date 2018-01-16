package com.zcy.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zcy.dao.LinkManDao;
import com.zcy.entity.LinkMan;

@Transactional
public class LinkManService {

	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	public void addLinkMan(LinkMan linkMan) {
		// TODO Auto-generated method stub
		linkManDao.add(linkMan);
	}

	public List<LinkMan> listLinkMan() {
		// TODO Auto-generated method stub
		return linkManDao.list();
	}

	public LinkMan findOne(int linkid) {
		// TODO Auto-generated method stub
		return linkManDao.findOne(linkid);
	}

	public void updateLinkMan(LinkMan linkman) {
		// TODO Auto-generated method stub
		linkManDao.updateLinkMan(linkman);
	}

	public List<LinkMan> findCondition(LinkMan linkman) {
		// TODO Auto-generated method stub
		return linkManDao.findCondition(linkman);
	}
	
}
