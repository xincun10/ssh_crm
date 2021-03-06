package com.zcy.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zcy.dao.CustomerDao;
import com.zcy.entity.Customer;
import com.zcy.entity.Dict;
import com.zcy.entity.PageBean;

@Transactional
public class CustomerService {

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void add(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.add(customer);
	}

	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerDao.findAll();
	}

	public Customer findOne(int cid) {
		// TODO Auto-generated method stub
		return customerDao.findOne(cid);
	}

	public void delete(Customer c) {
		// TODO Auto-generated method stub
		customerDao.delete(c);
	}

	public void update(Customer c) {
		// TODO Auto-generated method stub
		customerDao.update(c);
	}

	//封装分页数据到pagebean对象里面
	public PageBean listpage(Integer currentPage) {
		//创建PageBean对象
		PageBean pageBean = new PageBean();
		//当前页
		pageBean.setCurrentPage(currentPage);
		//总记录数
		int totalCount = customerDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		//每页显示记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//总页数
		int totalPage = 0;
		if(totalCount%pageSize==0)//能够整除
		{
			totalPage = totalCount/pageSize;
		}
		else
		{
			totalPage = totalCount/pageSize+1;
		}
		pageBean.setTotalPage(totalPage);
		
		//开始位置
		int begin = (currentPage-1)*pageSize;
		
		//每页记录的list集合
		List<Customer> list = customerDao.findPage(begin, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	public List<Customer> findCondition(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.findCondition(customer);
	}

	public List<Customer> findMoreCondition(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.findMoreCondition(customer);
	}

	public List<Dict> findAllDictLevel() {
		// TODO Auto-generated method stub
		return customerDao.findAllDictLevel();
	}

	public List findCountSource() {
		return customerDao.findCountSource();
	}

	public List findCountLevel() {
		return customerDao.findCountLevel();
	}
}
