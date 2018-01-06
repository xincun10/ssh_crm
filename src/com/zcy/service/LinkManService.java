package com.zcy.service;

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
	
}
