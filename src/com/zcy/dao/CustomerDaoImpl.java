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

	//查询所有级别
	public List<Dict> findAllDictLevel() {
		return (List<Dict>) this.getHibernateTemplate().find("from Dict");
	}

	//根据客户来源统计
	public List findCountSource() {
		// 因为写复杂语句，建议直接调用底层sql实现
		//SQLQuery对象
		//1.得到sessionFactory对象
		Session session = this.getSessionFactory().getCurrentSession();//与本地线程绑定的session
		String sql = "select count(*) as num,custSource from t_customer group by custSource";
		//2.创建SQLQuery对象
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		/*
		 * 之前的做法是放到实体类对象里面，
		 * sqlQuery.addEntity(实体类class);
		 * 但是现在返回值里面只有两个字段，一个是num，一个是名称,
		 * 所以把返回数据装换成map结构
		 */
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		//调用方法得到结果
		List list = sqlQuery.list();
		
		return list;
	}

	//根据客户级别进行查询
	public List findCountLevel() {
		String sql = "select c.num,d.dname from "
				+ "(select count(*) as num, custLevel from t_customer group by custLevel) c,"
				+ "t_dict d "
				+ "where c.custLevel=d.did";
		Session session = this.getSessionFactory().getCurrentSession();
		//创建SQLQuery对象
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		//得到结果
		//转换map结构
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		
		return sqlQuery.list();
	}

}
