package com.zcy.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

	//��������ѯ--hql��ѯ
//	public List<LinkMan> findCondition(LinkMan linkman) {
//		String hql = "from LinkMan where 1=1 ";
//		List<Object> p = new ArrayList<Object>();
//		//�ж�����ֵ�Ƿ�Ϊ��
//		if(linkman.getLkmName()!=null && !"".equals(linkman.getLkmName()))
//		{
//			hql += " and lkmName=?";
//			p.add(linkman.getLkmName());
//		}
//		//�ж��Ƿ�ѡ��ͻ�
//		if(linkman.getCustomer().getCid()!=null && linkman.getCustomer().getCid()>0)
//		{
//			//�жϿͻ�����cidֵ
//			hql += " and customer.cid=?";
//			p.add(linkman.getCustomer().getCid());
//		}
//		return (List<LinkMan>) this.getHibernateTemplate().find(hql, p.toArray());
//	}
	
	//���߶����ѯ
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
