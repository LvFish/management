package com.hsh.struts2;

import com.hsh.dao.UserDao;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction {
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
		if(user.login(username, password))
			return "success";
		else{
			ActionContext.getContext().put("msg", "账号或密码错误");
			return "failed";
		}
	}
}
