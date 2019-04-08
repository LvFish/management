package com.hsh.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hsh.entity.Project1;
import com.hsh.entity.Task;
import com.hsh.entity.User;


public class InfoDao extends BaseDao{
	public List<String> getPorjectTitleList(){
		final String[] titles = {"项目名","创建时间","创建人","负责人","项目状态","修改时间"};
		List<String> list = new ArrayList<>();
		list = Arrays.asList(titles);

		return list;
	}
	
	public List<String> getTaskTitleList(){
		final String[] titles = {"任务名","负责人","创建时间","创建人","修改时间"};
		List<String> list = new ArrayList<>();
		list = Arrays.asList(titles);
		return list;
	}
	
	public List<String> getPersonTitleList(){
		final String[] titles = {"人名"};
		List<String> list = new ArrayList<>();
		list = Arrays.asList(titles);
		return list;
	}
	
	public List<Project1> getAllProject() throws ClassNotFoundException, SQLException{
		ProjectDao projectDao = new ProjectDao();
		return projectDao.queryAll();
	}
	
	public List<Project1> getByProjectName(String name) throws ClassNotFoundException, SQLException{
		ProjectDao projectDao = new ProjectDao();
		return projectDao.queryByPName(name);
	}
	
	public List<Task> getAllTask() throws ClassNotFoundException, SQLException{
		TaskDao taskDao = new TaskDao();
		return taskDao.queryAll();
	}
	
	public List<Task> getTaskByName(String name) throws ClassNotFoundException, SQLException{
		TaskDao taskDao = new TaskDao();
		return taskDao.queryByName(name);
	}
	
	public List<User> getAllUser() throws ClassNotFoundException, SQLException{
		UserDao userDao = new UserDao();
		return userDao.queryAll();
	}
	
	public List<User> getUserByName(String name) throws ClassNotFoundException, SQLException{
		UserDao userDao = new UserDao();
		return userDao.queryByUName(name);
	}
}
