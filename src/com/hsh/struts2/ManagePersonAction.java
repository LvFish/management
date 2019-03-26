package com.hsh.struts2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hsh.dao.PersonnelDao;
import com.hsh.dao.ProjectDao;
import com.hsh.entity.Personnel;
import com.hsh.entity.Project1;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class ManagePersonAction implements Action{
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
//		HttpSession session = ServletActionContext.getRequest().getSession(); 
		Map<String,Object> map =  ActionContext.getContext().getSession();
		String perm = String.valueOf( map.get("permission"));
		System.out.println(perm);
		if(perm.length()<=0||perm.equals("null")){
			return "failed";
		}
		int permission = Integer.valueOf(perm);
//		int permission = Integer.valueOf((String) session.getAttribute("permission"));
		List<Project1> list = new ArrayList<>();
//		PersonnelDao personnelDao = new PersonnelDao();
		ProjectDao projectDao = new ProjectDao();
		if(permission == 1){
			list = projectDao.queryAll();
		}else{
			list = projectDao.queryByHeaderId(String.valueOf(map.get("uid")));
		}
		map.put("projectList", list);
		return "success";
	}
	
	public String queryByPId()throws Exception{
//		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		String pId = request.getParameter("pId");
		String pName = request.getParameter("pName");
		System.out.println("pId pName = "+pId+" "+pName);
		PersonnelDao personnelDao = new PersonnelDao();
		List<Personnel> list = new ArrayList<>();
		list = personnelDao.queryByPId(Integer.valueOf(pId));
		Map<String,Object> map =  ActionContext.getContext().getSession();
		map.put("personnelList", list);
		map.put("pName", pName);
		return "success";
	}
	
	public String deleteById() throws ClassNotFoundException, SQLException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Map<String,Object> map =  ActionContext.getContext().getSession();
		List<Personnel> list = (List<Personnel>) map.get("personnelList");
		for(int i=0;i<list.size();i++){
			if(list.get(i).getId() == Integer.valueOf(id)){
				list.remove(i);break;
			}
		}
		PersonnelDao personnelDao = new PersonnelDao();
		personnelDao.deleteById(Integer.valueOf(id));
		map.put("personnelList", list);
		return "success";
	}
	
	public String editStatus()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String status = request.getParameter("status");
		String uid = request.getParameter("uid");
		PersonnelDao personnelDao = new PersonnelDao();
		personnelDao.updateStatuts(Integer.valueOf(uid), status);
		return NONE;
	}
	

}
