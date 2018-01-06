package com.zcy.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zcy.entity.Customer;
import com.zcy.entity.LinkMan;
import com.zcy.service.CustomerService;
import com.zcy.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{

	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	//注入客户的service对象
	private CustomerService customerService;	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	//模型驱动获取linkMan
	private LinkMan linkMan = new LinkMan();
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}
	
	//实现文件上传
	//定义上传文件变量，变量名称需要是表单里面文件上传项的name值
	private File upload;
	//上传文件名称变量，变量名称需要是表单里面文件上传项的name值加上FileName
	private String uploadFileName;
	//生成get和set方法
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	//1.到新增联系人页面的方法
	public String toAddPage()
	{
		//得到所有客户
		//调用客户service里面的方法得到所有客户
		List<Customer> listCustomer = customerService.findAll();
		//放到域对象里面
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		return "toAddPage";
	}
	//2.添加数据到数据库
	public String addLinkMan() throws IOException
	{
		//判断是否需要上传文件
		if(upload != null)
		{
			//写上传代码
			//在服务器文件夹里面创建文件
			File serverFile = new File("E:/sshimg/"+uploadFileName);
			//把上传文件复制到服务器文件里面
			FileUtils.copyFile(upload, serverFile);
		}
		linkManService.addLinkMan(linkMan);
		return "addLinkMan";
	}
	
	
}
