package com.zcy.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class pClass;
	//���췽��
	public BaseDaoImpl() {
		//��һ�� �õ���ǰ������Class
		Class clazz = this.getClass();
		
		//�ڶ��� �õ�������ĸ���Ĳ���������BaseDaoImpl<Customer>
		//���÷���Type getGenericSuperclass()
		Type type = clazz.getGenericSuperclass();
		//ʹ��Type�ӽӿ�ParameterizedType
		ParameterizedType ptype = (ParameterizedType) type;
		
		//������ �õ�ʵ�����Ͳ�������<Cusomer>�����Customer
		//ʹ�÷���Type[] getActualTypeArguments()
		Type[] types = ptype.getActualTypeArguments();
		//Type�ӿڵ�ʵ����Class
		Class tclass = (Class) types[0];
		this.pClass = tclass;
	}
	
	//���
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	//�޸�
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	//ɾ��
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	//����id��ѯ
	@SuppressWarnings("all")
	public T findOne(int id) {
		//����дT.class
//		this.getHibernateTemplate().get(T.class, id);
		return (T) this.getHibernateTemplate().get(pClass, id);
	}

	//��ѯ����
	@SuppressWarnings("all")
	public List<T> findAll() {
		//��������д
//		this.getHibernateTemplate().find("from "+T);
		//ʹ��Class�����getSimpleName()���Եõ�������
		return (List<T>) this.getHibernateTemplate().find("from "+ pClass.getSimpleName());
	}

}
