package com.zcy.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zcy.entity.Customer;
import com.zcy.entity.Dict;
import com.zcy.entity.PageBean;
import com.zcy.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	private Customer customer = new Customer();
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	//根据来源统计
	public String countSource()
	{
		List list = customerService.findCountSource();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countSource";
	}
	
	//根据级别统计
	public String countLevel()
	{
		List list = customerService.findCountLevel();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countLevel";
	}
	
	//到客户信息查询页面
	public String toSelectCustomerPage()
	{
		//查询所有级别
		List<Dict> listDict = customerService.findAllDictLevel();
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		return "toSelectCustomerPage";
	}
	
	//多条件查询
	public String moreCondition()
	{
		List<Customer> list = customerService.findMoreCondition(customer);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
	
	//1.到添加页面
	public String toAddPage()
	{
		//查询所有级别
		List<Dict> listDict = customerService.findAllDictLevel();
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		return "toAddPage";
	}
	//2.添加的方法
	public String add()
	{
		customerService.add(customer);
		return "add";
	}
	//定义list变量
	private List<Customer> list;
	//生成变量的get方法
	public List<Customer> getList() {
		return list;
	}

	//3.查看客户列表的方法
	public String list()
	{
		//直接调用service的方法
//		List<Customer> list = customerService.findAll();
		//把list放到域对象里面
//		ServletActionContext.getRequest().setAttribute("list", list);
		//把数据放到值栈里面
		list = customerService.findAll();
		return "list";
	}

	//4.修改和删除的方法
	public String delete()
	{
		//使用模型驱动获取表单提交cid值
		int cid = customer.getCid();
		//首先根据id查询，调用方法删除
		Customer c = customerService.findOne(cid);
		if(c!=null)
			customerService.delete(c);
		return "delete";
	}
	//修改方法1-显示客户详细信息
	public String showCustomer()
	{
		//使用模型驱动获取表单提交cid值
		int cid = customer.getCid();
		//首先根据id查询，调用方法修改
		Customer c = customerService.findOne(cid);
		//将得到的对象放到域对象当中
		ServletActionContext.getRequest().setAttribute("customer", customer);
		return "showCustomer";
	}
	//修改方法2-更新客户信息
	public String update()
	{
		customerService.update(customer);
		return "update";
	}
	
	//使用属性封装获取当前页，模型驱动只能用一个
	private Integer currentPage;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	//分页的方法
	public String listPage()
	{
		//调用service方法实现封装
		PageBean pageBean = customerService.listpage(currentPage);
		//放到域对象里面
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listpage";
	}
	
	//条件查询
	public String listCondition()
	{
		//使用模型驱动方式获取客户名称
		if(customer.getCustName()!=null || "".equals(customer.getCustName()))
		{
			//不为空
			List<Customer> list = customerService.findCondition(customer);
			//放到域对象里面
			ServletActionContext.getRequest().setAttribute("list", list);
		}else
		{
			//不输入任何内容，默认查询所有
			list = customerService.findAll();//这个list为值栈里面的list
		}
		return "listCondition";
	}
	
}
