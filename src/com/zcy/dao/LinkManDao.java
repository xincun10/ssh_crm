package com.zcy.dao;

import java.util.List;

import com.zcy.entity.LinkMan;

public interface LinkManDao {

	void add(LinkMan linkMan);

	List<LinkMan> list();

	LinkMan findOne(int linkid);

	void updateLinkMan(LinkMan linkman);

}
