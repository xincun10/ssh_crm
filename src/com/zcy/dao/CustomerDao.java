package com.zcy.dao;

import java.util.List;

import com.zcy.entity.Customer;
import com.zcy.entity.Dict;

public interface CustomerDao extends BaseDao<Customer>{

//	void add(Customer customer);

//	List<Customer> findAll();

//	Customer findOne(int cid);

//	void delete(Customer c);

//	void update(Customer c);

	int findCount();

	List<Customer> findPage(int begin, int pageSize);

	List<Customer> findCondition(Customer customer);

	List<Customer> findMoreCondition(Customer customer);

	List<Dict> findAllDictLevel();

	List findCountSource();

	List findCountLevel();

}
