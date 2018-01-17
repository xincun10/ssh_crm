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

	//��װ��ҳ���ݵ�pagebean��������
	public PageBean listpage(Integer currentPage) {
		//����PageBean����
		PageBean pageBean = new PageBean();
		//��ǰҳ
		pageBean.setCurrentPage(currentPage);
		//�ܼ�¼��
		int totalCount = customerDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		//ÿҳ��ʾ��¼��
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//��ҳ��
		int totalPage = 0;
		if(totalCount%pageSize==0)//�ܹ�����
		{
			totalPage = totalCount/pageSize;
		}
		else
		{
			totalPage = totalCount/pageSize+1;
		}
		pageBean.setTotalPage(totalPage);
		
		//��ʼλ��
		int begin = (currentPage-1)*pageSize;
		
		//ÿҳ��¼��list����
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
