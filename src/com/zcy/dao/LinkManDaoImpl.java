package com.zcy.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

	//多条件查询--hql查询
//	public List<LinkMan> findCondition(LinkMan linkman) {
//		String hql = "from LinkMan where 1=1 ";
//		List<Object> p = new ArrayList<Object>();
//		//判断条件值是否为空
//		if(linkman.getLkmName()!=null && !"".equals(linkman.getLkmName()))
//		{
//			hql += " and lkmName=?";
//			p.add(linkman.getLkmName());
//		}
//		//判断是否选择客户
//		if(linkman.getCustomer().getCid()!=null && linkman.getCustomer().getCid()>0)
//		{
//			//判断客户里面cid值
//			hql += " and customer.cid=?";
//			p.add(linkman.getCustomer().getCid());
//		}
//		return (List<LinkMan>) this.getHibernateTemplate().find(hql, p.toArray());
//	}
	
	//离线对象查询
	public List<LinkMan> findCondition(LinkMan linkman)
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		if(linkman.getLkmName()!=null && !"".equals(linkman.getLkmName()))
		{
			criteria.add(Restrictions.eq("lkmName", linkman.getLkmName()));
		}
		if(linkman.getCustomer().getCid()!=null && linkman.getCustomer().getCid()>0)
		{
			criteria.add(Restrictions.eq("customer.cid", linkman.getCustomer().getCid()));
		}
		return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria);
	}

}
