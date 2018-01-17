package com.zcy.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zcy.entity.Customer;
import com.zcy.entity.Dict;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	//��ӿͻ�
//	public void add(Customer customer) {
//		this.getHibernateTemplate().save(customer);
//	}

//	@SuppressWarnings("all")
//	public List<Customer> findAll() {
//		// TODO Auto-generated method stub
//		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
//	}

	//����id��ѯ
//	@Override
//	public Customer findOne(int cid) {
//		return this.getHibernateTemplate().get(Customer.class, cid);
//	}

	//ɾ���û�
//	@Override
//	public void delete(Customer c) {
//		// TODO Auto-generated method stub
//		this.getHibernateTemplate().delete(c);
//	}

	//�޸Ŀͻ�
//	public void update(Customer c) {
//		// TODO Auto-generated method stub
//		this.getHibernateTemplate().update(c);
//	}

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

	//��������ѯ--ƴ��hql���ʵ��
//	public List<Customer> findMoreCondition(Customer customer) {
//		//ʹ��hibernateģ������find����ʵ��
//		//ƴ��hql���
//		String hql = "from Customer where 1=1";
//		//����list����
//		List<Object> p = new ArrayList<Object>();
//		//�ж�����ֵ�Ƿ�Ϊ�գ������Ϊ��ƴ��hql���
//		if(customer.getCustName()!=null && !"".equals(customer.getCustName()))
//		{
//			//ƴ��hql
//			hql += " and custName=?";
//			//��ֵ���õ�list����
//			p.add(customer.getCustName());
//		}
//		if(customer.getCustLevel()!=null && !"".equals(customer.getCustLevel()))
//		{
//			//ƴ��hql
//			hql += " and custLevel=?";
//			p.add(customer.getCustLevel());
//		}
//		if(customer.getCustSource()!=null && !"".equals(customer.getCustSource()))
//		{
//			//ƴ��hql
//			hql += " and custSource=?";
//			p.add(customer.getCustSource());
//		}
//		
//		return (List<Customer>) this.getHibernateTemplate().find(hql, p.toArray());
//	}
	//ʹ�����߶���ʽʵ�ֶ�������ϲ�ѯ
	public List<Customer> findMoreCondition(Customer customer) 
	{
		//�������߶���ָ�����ĸ�ʵ������в���
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//�ж�����ֵ�Ƿ�Ϊ��
		if(customer.getCustName()!=null && !"".equals(customer.getCustName()))
		{
			criteria.add(Restrictions.eq("custName", customer.getCustName()));
		}
		if(customer.getDictCustLevel().getDid()!=null && !"0".equals(customer.getDictCustLevel().getDid()))
		{
			criteria.add(Restrictions.eq("dictCustLevel.did", customer.getDictCustLevel().getDid()));
		}
		if(customer.getCustSource()!=null && !"".equals(customer.getCustSource()))
		{
			criteria.add(Restrictions.eq("custSource", customer.getCustSource()));
		}
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	//��ѯ���м���
	public List<Dict> findAllDictLevel() {
		return (List<Dict>) this.getHibernateTemplate().find("from Dict");
	}

	//���ݿͻ���Դͳ��
	public List findCountSource() {
		// ��Ϊд������䣬����ֱ�ӵ��õײ�sqlʵ��
		//SQLQuery����
		//1.�õ�sessionFactory����
		Session session = this.getSessionFactory().getCurrentSession();//�뱾���̰߳󶨵�session
		String sql = "select count(*) as num,custSource from t_customer group by custSource";
		//2.����SQLQuery����
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		/*
		 * ֮ǰ�������Ƿŵ�ʵ����������棬
		 * sqlQuery.addEntity(ʵ����class);
		 * �������ڷ���ֵ����ֻ�������ֶΣ�һ����num��һ��������,
		 * ���԰ѷ�������װ����map�ṹ
		 */
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		//���÷����õ����
		List list = sqlQuery.list();
		
		return list;
	}

	//���ݿͻ�������в�ѯ
	public List findCountLevel() {
		String sql = "select c.num,d.dname from "
				+ "(select count(*) as num, custLevel from t_customer group by custLevel) c,"
				+ "t_dict d "
				+ "where c.custLevel=d.did";
		Session session = this.getSessionFactory().getCurrentSession();
		//����SQLQuery����
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		//�õ����
		//ת��map�ṹ
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		
		return sqlQuery.list();
	}

}
