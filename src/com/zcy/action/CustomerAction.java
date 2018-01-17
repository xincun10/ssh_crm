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
	
	//������Դͳ��
	public String countSource()
	{
		List list = customerService.findCountSource();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countSource";
	}
	
	//���ݼ���ͳ��
	public String countLevel()
	{
		List list = customerService.findCountLevel();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countLevel";
	}
	
	//���ͻ���Ϣ��ѯҳ��
	public String toSelectCustomerPage()
	{
		//��ѯ���м���
		List<Dict> listDict = customerService.findAllDictLevel();
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		return "toSelectCustomerPage";
	}
	
	//��������ѯ
	public String moreCondition()
	{
		List<Customer> list = customerService.findMoreCondition(customer);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
	
	//1.�����ҳ��
	public String toAddPage()
	{
		//��ѯ���м���
		List<Dict> listDict = customerService.findAllDictLevel();
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		return "toAddPage";
	}
	//2.��ӵķ���
	public String add()
	{
		customerService.add(customer);
		return "add";
	}
	//����list����
	private List<Customer> list;
	//���ɱ�����get����
	public List<Customer> getList() {
		return list;
	}

	//3.�鿴�ͻ��б�ķ���
	public String list()
	{
		//ֱ�ӵ���service�ķ���
//		List<Customer> list = customerService.findAll();
		//��list�ŵ����������
//		ServletActionContext.getRequest().setAttribute("list", list);
		//�����ݷŵ�ֵջ����
		list = customerService.findAll();
		return "list";
	}

	//4.�޸ĺ�ɾ���ķ���
	public String delete()
	{
		//ʹ��ģ��������ȡ���ύcidֵ
		int cid = customer.getCid();
		//���ȸ���id��ѯ�����÷���ɾ��
		Customer c = customerService.findOne(cid);
		if(c!=null)
			customerService.delete(c);
		return "delete";
	}
	//�޸ķ���1-��ʾ�ͻ���ϸ��Ϣ
	public String showCustomer()
	{
		//ʹ��ģ��������ȡ���ύcidֵ
		int cid = customer.getCid();
		//���ȸ���id��ѯ�����÷����޸�
		Customer c = customerService.findOne(cid);
		//���õ��Ķ���ŵ��������
		ServletActionContext.getRequest().setAttribute("customer", customer);
		return "showCustomer";
	}
	//�޸ķ���2-���¿ͻ���Ϣ
	public String update()
	{
		customerService.update(customer);
		return "update";
	}
	
	//ʹ�����Է�װ��ȡ��ǰҳ��ģ������ֻ����һ��
	private Integer currentPage;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	//��ҳ�ķ���
	public String listPage()
	{
		//����service����ʵ�ַ�װ
		PageBean pageBean = customerService.listpage(currentPage);
		//�ŵ����������
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listpage";
	}
	
	//������ѯ
	public String listCondition()
	{
		//ʹ��ģ��������ʽ��ȡ�ͻ�����
		if(customer.getCustName()!=null || "".equals(customer.getCustName()))
		{
			//��Ϊ��
			List<Customer> list = customerService.findCondition(customer);
			//�ŵ����������
			ServletActionContext.getRequest().setAttribute("list", list);
		}else
		{
			//�������κ����ݣ�Ĭ�ϲ�ѯ����
			list = customerService.findAll();//���listΪֵջ�����list
		}
		return "listCondition";
	}
	
}
