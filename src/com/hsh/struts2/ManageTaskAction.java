package com.hsh.struts2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hsh.dao.PersonnelDao;
import com.hsh.dao.ProjectDao;
import com.hsh.dao.TaskDao;
import com.hsh.entity.Personnel;
import com.hsh.entity.Project1;
import com.hsh.entity.Task;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class ManageTaskAction implements Action{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map =  ActionContext.getContext().getSession();
		String perm = String.valueOf( map.get("permission"));
		System.out.println(perm);
		if(perm.length()<=0 || perm.equals("null")){
			return "failed";
		}
		int permission = Integer.valueOf(perm);
//		int permission = Integer.valueOf((String) session.getAttribute("permission"));
		List<Project1> list = new ArrayList<>();
//		PersonnelDao personnelDao = new PersonnelDao();
		ProjectDao projectDao = new ProjectDao();
		TaskDao taskDao = new TaskDao();
		if(permission == 1){
			list = projectDao.queryAll();
		}else{
			list = taskDao.queryById(Integer.valueOf(String.valueOf(map.get("uid"))));
		}
		map.put("projectList", list);
		return "success";
	}

	public String queryTaskByPId() throws NumberFormatException, ClassNotFoundException, SQLException{
//		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		String pId = request.getParameter("pId");
		String pName = request.getParameter("pName");
		System.out.println("pId pName = "+pId+" "+pName);
		TaskDao taskDao = new TaskDao();
		List<Task> list = new ArrayList<>();
		list = taskDao.queryTaskByPId(Integer.valueOf(pId));
//		list = personnelDao.queryByPId(Integer.valueOf(pId));
		Map<String,Object> map =  ActionContext.getContext().getSession();
		map.put("taskList", list);
//		System.out.println(list.size());
		map.put("pName", pName);
		return "success";
	}
	
	public String deleteTaskByTId() throws NumberFormatException, ClassNotFoundException, SQLException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Map<String,Object> map =  ActionContext.getContext().getSession();
		List<Task> list = (List<Task>) map.get("taskList");
		for(int i=0;i<list.size();i++){
			if(list.get(i).getTid().equals(id)){
				list.remove(i);break;
			}
		}
		TaskDao taskDao = new TaskDao();
		taskDao.deleteTaskByTId(Integer.valueOf(id));
		map.put("taskList", list);
		return "success";
	}
	
}
