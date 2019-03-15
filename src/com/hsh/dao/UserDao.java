package com.hsh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class UserDao extends BaseDao{
	public boolean login(String username, String password) throws ClassNotFoundException, SQLException{
		boolean flag = false;
		Connection connention = getConn();
		Statement statement = connention.createStatement();
		String sql = "SELECT * FROM user where username = '"+username+"' and password='"+password+"'"; //准备sql语句
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()){
			flag = true;
			int permission = resultSet.getInt(4);
			Map<String, Object> session = ActionContext.getContext().getSession();  //记录用户登录信息 
			session.put("username", username); 
			session.put("permission", permission);
//			System.out.println(resultSet.getInt(1));
		}
			//关闭（倒关）
		resultSet.close();
		statement.close();
		connention.close();
		return flag;
	}
}
