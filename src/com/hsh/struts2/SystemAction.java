package com.hsh.struts2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hsh.dao.UserDao;
import com.hsh.entity.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class SystemAction implements Action{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map =  ActionContext.getContext().getSession();
		String permission = String.valueOf(map.get("permission"));
		if(permission!=null&&permission.equals("1")){
			UserDao userDao = new UserDao();
			map.put("userList", userDao.queryAll());
			return "success";
		}
		map.put("msg_system", "请登录管理员账号");
		return "failed";
	}
	
	public String editUserPassword() throws ClassNotFoundException, SQLException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String uid = request.getParameter("uid");
		String password = request.getParameter("password");
		UserDao userDao = new UserDao();
		System.out.println("uid: "+uid+" "+"password: "+password);
		userDao.editUserPassword(uid, password);
		return null;
	}

	public String userSearch() throws ClassNotFoundException, SQLException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("uName");
		UserDao userDao = new UserDao();
		Map<String,Object> map =  ActionContext.getContext().getSession();
		map.put("userList", userDao.queryByUName(username));
		return "success";
	}
	public String addUser() throws ClassNotFoundException, SQLException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("uName");
		UserDao userDao = new UserDao();
		userDao.addUser(username);
		Map<String,Object> map =  ActionContext.getContext().getSession();
		map.put("userList", userDao.queryAll());
		return "success";
	}
}
