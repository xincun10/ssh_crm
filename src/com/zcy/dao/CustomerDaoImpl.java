package com.zcy.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zcy.entity.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	//添加客户
//	public void add(Customer customer) {
//		this.getHibernateTemplate().save(customer);
//	}

//	@SuppressWarnings("all")
//	public List<Customer> findAll() {
//		// TODO Auto-generated method stub
//		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
//	}

	//根据id查询
//	@Override
//	public Customer findOne(int cid) {
//		return this.getHibernateTemplate().get(Customer.class, cid);
//	}

	//删除用户
//	@Override
//	public void delete(Customer c) {
//		// TODO Auto-generated method stub
//		this.getHibernateTemplate().delete(c);
//	}

	//修改客户
//	public void update(Customer c) {
//		// TODO Auto-generated method stub
//		this.getHibernateTemplate().update(c);
//	}

	//查询记录数
	public int findCount() {
		//调用hibernateTemplate里面的find方法实现
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		//从list里面得到相应的值
		if(list!=null && list.size()!=0)
		{
			Object obj = list.get(0);
			//转换成int类型
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}

	//分页查询
//	public List<Customer> findPage(int begin, int pageSize) {
//		// 第一种方法，使用hibernate底层代码实现（了解）
//		//得到SessionFactory
//		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//		//得到与本地线程绑定的session对象
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
		//第二种 使用离线对象和hibernateTemplate的方法实现
		//1.创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//2.调用hibernatetemplate的方法实现
		List<Customer> list = 
				(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	//实现条件查询
//	public List<Customer> findCondition(Customer customer) {
//		//第一种方式(不常用)
//		//得到SessionFactory
//		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//		//得到与本地线程绑定的session对象
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("from Customer where custName like ?");
//		query.setParameter(0, "%"+customer.getCustName()+"%");
//		List<Customer> list = query.list();
//		return list;		
//	}
//	public List<Customer> findCondition(Customer customer)
//	{
//		//第二种方式,调用hibernatetemplate的find方法
//		List<Customer> list = 
//				(List<Customer>) this.getHibernateTemplate()
//				.find("from Customer where custName like ?", "%"+customer.getCustName()+"%");
//		return list;
//	}
	public List<Customer> findCondition(Customer customer)
	{
		//第三种方式
		//1.创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//2.设置对实体类的哪个属性
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		//3.调用hibernatetemplate里面的方法得到list集合
		List<Customer> list = 
				(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	//多条件查询--拼接hql语句实现
//	public List<Customer> findMoreCondition(Customer customer) {
//		//使用hibernate模板里面find方法实现
//		//拼接hql语句
//		String hql = "from Customer where 1=1";
//		//创建list集合
//		List<Object> p = new ArrayList<Object>();
//		//判断条件值是否为空，如果不为空拼接hql语句
//		if(customer.getCustName()!=null && !"".equals(customer.getCustName()))
//		{
//			//拼接hql
//			hql += " and custName=?";
//			//把值设置到list里面
//			p.add(customer.getCustName());
//		}
//		if(customer.getCustLevel()!=null && !"".equals(customer.getCustLevel()))
//		{
//			//拼接hql
//			hql += " and custLevel=?";
//			p.add(customer.getCustLevel());
//		}
//		if(customer.getCustSource()!=null && !"".equals(customer.getCustSource()))
//		{
//			//拼接hql
//			hql += " and custSource=?";
//			p.add(customer.getCustSource());
//		}
//		
//		return (List<Customer>) this.getHibernateTemplate().find(hql, p.toArray());
//	}
	//使用离线对象方式实现多条件组合查询
	public List<Customer> findMoreCondition(Customer customer) 
	{
		//创建离线对象，指定对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//判断条件值是否为空
		if(customer.getCustName()!=null && !"".equals(customer.getCustName()))
		{
			criteria.add(Restrictions.eq("custName", customer.getCustName()));
		}
		if(customer.getCustLevel()!=null && !"".equals(customer.getCustLevel()))
		{
			criteria.add(Restrictions.eq("custLevel", customer.getCustLevel()));
		}
		if(customer.getCustSource()!=null && !"".equals(customer.getCustSource()))
		{
			criteria.add(Restrictions.eq("custSource", customer.getCustSource()));
		}
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
	}

}
