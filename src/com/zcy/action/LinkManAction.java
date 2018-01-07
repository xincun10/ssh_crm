package com.zcy.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	//ע��ͻ���service����
	private CustomerService customerService;	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	//ģ��������ȡlinkMan
	private LinkMan linkman = new LinkMan();
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkman;
	}
	
	//ʵ���ļ��ϴ�
	//�����ϴ��ļ�����������������Ҫ�Ǳ������ļ��ϴ����nameֵ
	private File upload;
	//�ϴ��ļ����Ʊ���������������Ҫ�Ǳ������ļ��ϴ����nameֵ����FileName
	private String uploadFileName;
	//����get��set����
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

	//1.��������ϵ��ҳ��ķ���
	public String toAddPage()
	{
		//�õ����пͻ�
		//���ÿͻ�service����ķ����õ����пͻ�
		List<Customer> listCustomer = customerService.findAll();
		//�ŵ����������
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		return "toAddPage";
	}
	//2.������ݵ����ݿ�
	public String addLinkMan() throws IOException
	{
		//�ж��Ƿ���Ҫ�ϴ��ļ�
		if(upload != null)
		{
			//д�ϴ�����
			//�ڷ������ļ������洴���ļ�
			File serverFile = new File("E:/sshimg/"+uploadFileName);
			//���ϴ��ļ����Ƶ��������ļ�����
			FileUtils.copyFile(upload, serverFile);
		}
		linkManService.addLinkMan(linkman);
		return "addLinkMan";
	}
	//3.��ϵ���б�ķ���
	public String list()
	{
		List<LinkMan> list = linkManService.listLinkMan();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	//4.���޸���ϵ��ҳ��
	public String showLinkMan()
	{
		//ʹ��ģ�������õ�idֵ
		int linkid = linkman.getLinkid();
		//����idֵ��ѯ��ϵ��
		LinkMan link = linkManService.findOne(linkid);
		//�õ����пͻ��б�
		List<Customer> listCustomer = customerService.findAll();
		//����ϵ�˺Ϳͻ��ŵ����������
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("linkman", link);
		request.setAttribute("listCustomer", listCustomer);
		
		return "showLinkMan";
	}
	//5.�޸���ϵ��
	public String updateLinkMan()
	{
		//ģ��������ȡ��ϵ�˵�ֵ
		linkManService.updateLinkMan(linkman);
		return "updateLinkMan";
	}
	
}
