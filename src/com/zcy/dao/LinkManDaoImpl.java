package com.zcy.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zcy.entity.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	//�����ϵ�˵ķ���
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}

	//��ʾ��ϵ���б�
	public List<LinkMan> list() {
		return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
	}

	//����id��ѯ��ϵ��
	public LinkMan findOne(int linkid) {
		return this.getHibernateTemplate().get(LinkMan.class, linkid);
	}

	//������ϵ��ֵ
	public void updateLinkMan(LinkMan linkman) {
		this.getHibernateTemplate().update(linkman);
	}

}
