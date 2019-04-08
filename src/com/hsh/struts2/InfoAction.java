package com.hsh.struts2;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hsh.dao.InfoDao;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class InfoAction implements Action {

	private InfoDao infoDao;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ActionContext.getContext().put("info_type", "0");
		return "success";
	}

	public String queryInfo()throws Exception{
		infoDao = new InfoDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		if(name == null){
			name = "";
		}
		List<String> titleList = new ArrayList<>();
		if(type.equals("project")){
			titleList = infoDao.getPorjectTitleList();
			ActionContext.getContext().put("info_type", "1");
			ActionContext.getContext().put("projectList", infoDao.getByProjectName(name));
		}else if(type.equals("task")){
			titleList = infoDao.getTaskTitleList();
			ActionContext.getContext().put("info_type", "2");
			ActionContext.getContext().put("taskList", infoDao.getTaskByName(name));
		}else if(type.equals("person")){
			titleList = infoDao.getPersonTitleList();
			ActionContext.getContext().put("info_type", "3");
			ActionContext.getContext().put("userList", infoDao.getUserByName(name));
		}
		ActionContext.getContext().put("titleList", titleList);
		return "success";
	}
	
}
