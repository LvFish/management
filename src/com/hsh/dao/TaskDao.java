package com.hsh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hsh.entity.Project1;
import com.hsh.entity.Task;

public class TaskDao extends BaseDao{
	public List<Project1> queryById(int id) throws ClassNotFoundException, SQLException{
		String sql = "select distinct p.*,u1.username,u2.username from project p "
				+ "left join task t on t.pid = p.pid "
				+ "left join inner_task_user tu on tu.tid = t.tid "
				+ "left join user u on u.uid = tu.uid "
				+ "left join user u1 on u1.uid = p.createBy "
				+ "left join user u2 on u2.uid = p.headerId "
				+ "where p.headerId = "+id+" or u.uid = "+id+" order by p.pid";
		Connection conn = getConn();
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Project1> list = new ArrayList<>();
		while (resultSet.next()) {
			Project1 p = new Project1();
			p.setPid(resultSet.getInt(1));
			p.setpName(resultSet.getString(2));
			p.setCreate(resultSet.getString(3));
			p.setCreateBy(resultSet.getInt(4));
			p.setHeadId(resultSet.getInt(5));
			p.setStatus(resultSet.getString(6));
			p.setModifyTime(resultSet.getString(7));
			p.setCreateByName(resultSet.getString(8));
			p.setHeadName(resultSet.getString(9));
			list.add(p);
		}
		// 关闭（倒关）
		resultSet.close();
		statement.close();
		conn.close();
		return list;
	}
	
	
	
	public List<Task> queryTaskByPId(int pid) throws ClassNotFoundException, SQLException{
		Connection conn = getConn();
		String sql = "SELECT t.*,u.username,u.uid,p.pName,u1.username FROM task t "
				+ "LEFT JOIN inner_task_user tu ON t.tid=tu.tid "
				+ "LEFT JOIN USER u ON tu.uid=u.uid "
				+ "LEFT JOIN USER u1 ON u1.uid=t.createBy "
				+ "LEFT JOIN project p ON p.pid=t.pid "
				+ "WHERE t.pid = "+pid;
		System.out.println(sql);
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Task> list = new ArrayList<>();
		while(resultSet.next()){
			Task task = new Task();
			task.setTid(resultSet.getString(1));
			task.setTaskName(resultSet.getString(2));
			task.setCreate(resultSet.getString(3));
			task.setCreateBy(resultSet.getInt(4));
			task.setPid(resultSet.getInt(5));
			task.setStatus(resultSet.getString(6));
			task.setModifyTime(resultSet.getString(7));
			task.setuName(resultSet.getString(8));
			task.setuId(resultSet.getString(9));
			task.setpName(resultSet.getString(10));
			task.setCreateByName(resultSet.getString(11));
			list.add(task);
		}
		resultSet.close();
		statement.close();
		conn.close();
		return list;
	}
	
	public List<Task> queryAll() throws ClassNotFoundException, SQLException{
		Connection conn = getConn();
		String sql = "SELECT t.*,u.username,u.uid,p.pName,u1.username FROM task t "
				+ "LEFT JOIN inner_task_user tu ON t.tid=tu.tid "
				+ "LEFT JOIN USER u ON tu.uid=u.uid "
				+ "LEFT JOIN USER u1 ON u1.uid=t.createBy "
				+ "LEFT JOIN project p ON p.pid=t.pid ";
		System.out.println(sql);
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Task> list = new ArrayList<>();
		while(resultSet.next()){
			Task task = new Task();
			task.setTid(resultSet.getString(1));
			task.setTaskName(resultSet.getString(2));
			task.setCreate(resultSet.getString(3));
			task.setCreateBy(resultSet.getInt(4));
			task.setPid(resultSet.getInt(5));
			task.setStatus(resultSet.getString(6));
			task.setModifyTime(resultSet.getString(7));
			task.setuName(resultSet.getString(8));
			task.setuId(resultSet.getString(9));
			task.setpName(resultSet.getString(10));
			task.setCreateByName(resultSet.getString(11));
			list.add(task);
		}
		resultSet.close();
		statement.close();
		conn.close();
		return list;
	}
	
	public List<Task> queryByName(String name) throws ClassNotFoundException, SQLException{
		Connection conn = getConn();
		String sql = "SELECT t.*,u.username,u.uid,p.pName,u1.username FROM task t "
				+ "LEFT JOIN inner_task_user tu ON t.tid=tu.tid "
				+ "LEFT JOIN USER u ON tu.uid=u.uid "
				+ "LEFT JOIN USER u1 ON u1.uid=t.createBy "
				+ "LEFT JOIN project p ON p.pid=t.pid "
				+ "where t.taskName like '%"+name+"%'";
		System.out.println(sql);
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Task> list = new ArrayList<>();
		while(resultSet.next()){
			Task task = new Task();
			task.setTid(resultSet.getString(1));
			task.setTaskName(resultSet.getString(2));
			task.setCreate(resultSet.getString(3));
			task.setCreateBy(resultSet.getInt(4));
			task.setPid(resultSet.getInt(5));
			task.setStatus(resultSet.getString(6));
			task.setModifyTime(resultSet.getString(7));
			task.setuName(resultSet.getString(8));
			task.setuId(resultSet.getString(9));
			task.setpName(resultSet.getString(10));
			task.setCreateByName(resultSet.getString(11));
			list.add(task);
		}
		resultSet.close();
		statement.close();
		conn.close();
		return list;
	}
	
	public void deleteTaskByTId(int tid) throws ClassNotFoundException, SQLException{
		Connection conn = getConn();
		String sql = "delete from task where tid = "+tid;
		String sql2 = "delete from inner_task_user where tid = "+tid;
		PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	        pstmt.close();
	        pstmt = (PreparedStatement) conn.prepareStatement(sql2);
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void addTask(Task task) throws ClassNotFoundException, SQLException{
		Connection conn = getConn();
		String sql = "insert into task (taskName,`create`,createBy,pid,`status`,modifyTime) values(?,?,?,?,?,?)";
//		Task task = new Task();
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, task.getTaskName());
			pstmt.setString(2, task.getCreate());
			pstmt.setInt(3, task.getCreateBy());
			pstmt.setInt(4, task.getPid());
			pstmt.setString(5, task.getStatus());
			pstmt.setString(6, task.getModifyTime());
			System.out.println(pstmt.toString());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editUser(String tid,String uid,int type) throws ClassNotFoundException, SQLException{
		Connection conn = getConn();
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql,sql2;
		if(type == 0){
			sql = "insert into inner_task_user(tid,uid) values("+tid+","+uid+")";
		}else{
			sql = "update inner_task_user set  uid = "+uid+" where tid = "+tid;
		}
		sql2 = "update task set  modifyTime = '"+sft.format(new Date())+"' where tid = "+tid;
		PreparedStatement pstmt;
	    try {	
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	        pstmt = (PreparedStatement) conn.prepareStatement(sql2);
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
}
