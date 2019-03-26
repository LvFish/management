package com.hsh.struts2;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hsh.dao.ProjectDao;
import com.hsh.dao.UserDao;
import com.hsh.entity.Project1;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction implements Action{
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String execute() throws Exception {
		UserDao user = new UserDao();
		if(user.login(username, password)){
			ProjectDao projectDao = new ProjectDao();
			List<Project1> list = projectDao.queryAll();
			ActionContext.getContext().put("projectList",list);
			return "success";
		}
		else{
			if(username!=null)
				ActionContext.getContext().put("msg", "账号或密码错误");
			return "failed";
		}
	}
}
