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
	
	//1.到新增页面
	public String toAddPage()
	{
		//查询所有的客户
		List<Customer> listCustomer = customerService.findAll();
		//查询所有的用户
		List<User> listUser = userService.findAll();
		
		//放到域对象
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listCustomer", listCustomer);
		request.setAttribute("listUser", listUser);
		
		return "toAddPage";
	}
	
	//2.新增拜访记录
	public String addVisit()
	{
		visitService.addVisit(visit);
		return "addVisit";
	}
	
	//3.显示拜访记录列表
	public String list()
	{
		List<Visit> listVisit = visitService.findAll();
		ServletActionContext.getRequest().setAttribute("listVisit", listVisit);
		return "list";
	}
	
	//4.跳转到修改页面
	public String showVisit()
	{
		//获取拜访记录id
		int vid = visit.getVid();
		//查找vid这条记录
		Visit visit1 = visitService.findOne(vid);
		//得到所有客户
		List<Customer> listCustomer = customerService.findAll();
		//得到所有用户
		List<User> listUser = userService.findAll();
		//将记录放到域对象
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("visit", visit1);
		request.setAttribute("listUser", listUser);
		request.setAttribute("listCustomer", listCustomer);
		return "showVisit";
	}

	//5.修改联系人
	public String updateVisit()
	{
		visitService.update(visit);
		return "updateVisit";
	}
	
}
