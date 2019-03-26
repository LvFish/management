package com.hsh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hsh.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class UserDao extends BaseDao{
	public boolean login(String username, String password) throws ClassNotFoundException, SQLException{
		boolean flag = false;
		Connection connention = getConn();
		Statement statement = connention.createStatement();
		Map<String, Object> session = ActionContext.getContext().getSession();  //记录用户登录信息 
		String sql = "SELECT * FROM user where username = '"+username+"' and password='"+password+"'"; //准备sql语句
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()){
			flag = true;
			int permission = resultSet.getInt(4);
			
			session.put("username", username); 
			session.put("uid", resultSet.getInt(1)); 
			session.put("permission", permission);
//			System.out.println(resultSet.getInt(1));
		}
			//关闭（倒关）
		resultSet.close();
		statement.close();
		connention.close();
		return flag;
	}
	
	public List<User> queryAll() throws ClassNotFoundException, SQLException{
		Connection conn = getConn();
		List<User> userList = new ArrayList<>();
		Statement statement = conn.createStatement();
		String sql = "SELECT * FROM user "; //准备sql语句
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()){
			User user = new User();
			user.setuId(resultSet.getString(1));
			user.setUsername(resultSet.getString(2));
			user.setPassword(resultSet.getString(3));
			user.setPermission(resultSet.getInt(4));
			userList.add(user);
		}
			//关闭（倒关）
		resultSet.close();
		statement.close();
		conn.close();
		return userList;
	}
}
