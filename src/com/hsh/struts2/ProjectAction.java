package com.hsh.struts2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hsh.dao.ProjectDao;
import com.hsh.dao.UserDao;
import com.hsh.entity.Project1;
import com.hsh.entity.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class ProjectAction implements Action{
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("in project");
		ProjectDao projectDao = new ProjectDao();
		List<Project1> list = projectDao.queryAll();
		ActionContext.getContext().put("projectList",list);
		System.out.println(list.size());
		return "success";
	}
	
	public String queryByPName() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String pName = request.getParameter("pName");
		System.out.println(pName);
		ProjectDao projectDao = new ProjectDao();
		List<Project1> list = projectDao.queryByPName(pName);
		ActionContext.getContext().put("projectList",list);
		return "success";
	}
	
	public String deleteById()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String pId = request.getParameter("pId");
		System.out.println(pId);
		ProjectDao projectDao = new ProjectDao();
		projectDao.deleteById(Integer.valueOf(pId));
		List<Project1> list = projectDao.queryAll();
		ActionContext.getContext().put("projectList",list);
		return "success";
	}
	
	public String addProject()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String pName = request.getParameter("pName");
		System.out.println(pName);
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ProjectDao projectDao = new ProjectDao();
		Project1 project = new Project1();
		Map<String,Object> map =  ActionContext.getContext().getSession();
		project.setCreateBy(Integer.valueOf(String.valueOf(map.get("uid"))));
		project.setCreate(fmt.format(new Date()));
		project.setModifyTime(fmt.format(new Date()));
		project.setStatus("start");
		project.setpName(pName);
		projectDao.insert(project);
		List<Project1> list = projectDao.queryAll();
		ActionContext.getContext().put("projectList",list);
		return "success";
	}
	
	public String editProject()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String pid = request.getParameter("PId");
		ProjectDao projectDao = new ProjectDao();
		Project1 p = projectDao.queryById(pid);
		UserDao userDao = new UserDao();
		List<User> userList = userDao.queryAll();
		ActionContext.getContext().put("project",p);
		ActionContext.getContext().put("userList",userList);
		return "success";
	}

	public String updateProject()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String pName = request.getParameter("pName");
		String headId = request.getParameter("headId");
		String status = request.getParameter("status");
		String pid = request.getParameter("pid");
		ProjectDao projectDao = new ProjectDao();
		projectDao.updateById(Integer.valueOf(pid), pName, status, headId);
		Project1 p = projectDao.queryById(pid);
		ActionContext.getContext().put("project",p);
		UserDao userDao = new UserDao();
		List<User> userList = userDao.queryAll();
		ActionContext.getContext().put("userList",userList);
		return "success";
	}
}
