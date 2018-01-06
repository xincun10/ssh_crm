package com.zcy.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zcy.entity.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	//��ӿͻ�
	public void add(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	@SuppressWarnings("all")
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

	//����id��ѯ
	@Override
	public Customer findOne(int cid) {
		return this.getHibernateTemplate().get(Customer.class, cid);
	}

	//ɾ���û�
	@Override
	public void delete(Customer c) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(c);
	}

	//�޸Ŀͻ�
	public void update(Customer c) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(c);
	}

	//��ѯ��¼��
	public int findCount() {
		//����hibernateTemplate�����find����ʵ��
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		//��list����õ���Ӧ��ֵ
		if(list!=null && list.size()!=0)
		{
			Object obj = list.get(0);
			//ת����int����
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}

	//��ҳ��ѯ
//	public List<Customer> findPage(int begin, int pageSize) {
//		// ��һ�ַ�����ʹ��hibernate�ײ����ʵ�֣��˽⣩
//		//�õ�SessionFactory
//		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//		//�õ��뱾���̰߳󶨵�session����
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query query = session.createQuery("from Customer");
//		query.setFirstResult(begin);
//		query.setMaxResults(pageSize);
//		
//		List<Customer> list = query.list();
//		return list;
//	}
	public List<Customer> findPage(int begin, int pageSize)
	{
		//�ڶ��� ʹ�����߶����hibernateTemplate�ķ���ʵ��
		//1.�������߶������ö��ĸ�ʵ������в���
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//2.����hibernatetemplate�ķ���ʵ��
		List<Customer> list = 
				(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	//ʵ��������ѯ
//	public List<Customer> findCondition(Customer customer) {
//		//��һ�ַ�ʽ(������)
//		//�õ�SessionFactory
//		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//		//�õ��뱾���̰߳󶨵�session����
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("from Customer where custName like ?");
//		query.setParameter(0, "%"+customer.getCustName()+"%");
//		List<Customer> list = query.list();
//		return list;		
//	}
//	public List<Customer> findCondition(Customer customer)
//	{
//		//�ڶ��ַ�ʽ,����hibernatetemplate��find����
//		List<Customer> list = 
//				(List<Customer>) this.getHibernateTemplate()
//				.find("from Customer where custName like ?", "%"+customer.getCustName()+"%");
//		return list;
//	}
	public List<Customer> findCondition(Customer customer)
	{
		//�����ַ�ʽ
		//1.�������߶������ö��ĸ�ʵ������в���
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//2.���ö�ʵ������ĸ�����
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		//3.����hibernatetemplate����ķ����õ�list����
		List<Customer> list = 
				(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

}
