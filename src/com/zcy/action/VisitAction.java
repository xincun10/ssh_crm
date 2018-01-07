package com.zcy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zcy.entity.Customer;
import com.zcy.entity.User;
import com.zcy.entity.Visit;
import com.zcy.service.CustomerService;
import com.zcy.service.UserService;
import com.zcy.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{

	private VisitService visitService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private Visit visit = new Visit();
	public Visit getModel() {
		// TODO Auto-generated method stub
		return visit;
	}
	
	//1.������ҳ��
	public String toAddPage()
	{
		//��ѯ���еĿͻ�
		List<Customer> listCustomer = customerService.findAll();
		//��ѯ���е��û�
		List<User> listUser = userService.findAll();
		
		//�ŵ������
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listCustomer", listCustomer);
		request.setAttribute("listUser", listUser);
		
		return "toAddPage";
	}
	
	//2.�����ݷü�¼
	public String addVisit()
	{
		visitService.addVisit(visit);
		return "addVisit";
	}
	
	//3.��ʾ�ݷü�¼�б�
	public String list()
	{
		List<Visit> listVisit = visitService.findAll();
		ServletActionContext.getRequest().setAttribute("listVisit", listVisit);
		return "list";
	}
	
	//4.��ת���޸�ҳ��
	public String showVisit()
	{
		//��ȡ�ݷü�¼id
		int vid = visit.getVid();
		//����vid������¼
		Visit visit1 = visitService.findOne(vid);
		//�õ����пͻ�
		List<Customer> listCustomer = customerService.findAll();
		//�õ������û�
		List<User> listUser = userService.findAll();
		//����¼�ŵ������
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("visit", visit1);
		request.setAttribute("listUser", listUser);
		request.setAttribute("listCustomer", listCustomer);
		return "showVisit";
	}

	//5.�޸���ϵ��
	public String updateVisit()
	{
		visitService.update(visit);
		return "updateVisit";
	}
	
}
