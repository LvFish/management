package com.hsh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
	public boolean login(String username, String password) throws ClassNotFoundException, SQLException{
		boolean flag = false;
		Class.forName("com.mysql.jdbc.Driver");
		Connection connention = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "root");
		String sql = "SELECT * FROM user where username = '"+username+"' and password='"+password+"'"; //准备sql语句
		Statement statement = connention.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()){
			flag = true;
			System.out.println(resultSet.getInt(1));
		}
			//关闭（倒关）
		resultSet.close();
		statement.close();
		connention.close();
		return flag;
	}
}
