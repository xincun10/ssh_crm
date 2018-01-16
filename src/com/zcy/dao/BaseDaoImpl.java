package com.zcy.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class pClass;
	//构造方法
	public BaseDaoImpl() {
		//第一步 得到当前运行类Class
		Class clazz = this.getClass();
		
		//第二步 得到运行类的父类的参数化类型BaseDaoImpl<Customer>
		//利用方法Type getGenericSuperclass()
		Type type = clazz.getGenericSuperclass();
		//使用Type子接口ParameterizedType
		ParameterizedType ptype = (ParameterizedType) type;
		
		//第三步 得到实际类型参数，即<Cusomer>里面的Customer
		//使用方法Type[] getActualTypeArguments()
		Type[] types = ptype.getActualTypeArguments();
		//Type接口的实现类Class
		Class tclass = (Class) types[0];
		this.pClass = tclass;
	}
	
	//添加
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	//修改
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	//删除
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	//根据id查询
	@SuppressWarnings("all")
	public T findOne(int id) {
		//不能写T.class
//		this.getHibernateTemplate().get(T.class, id);
		return (T) this.getHibernateTemplate().get(pClass, id);
	}

	//查询所有
	@SuppressWarnings("all")
	public List<T> findAll() {
		//不能这样写
//		this.getHibernateTemplate().find("from "+T);
		//使用Class里面的getSimpleName()可以得到类名称
		return (List<T>) this.getHibernateTemplate().find("from "+ pClass.getSimpleName());
	}

}
