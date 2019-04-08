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

import com.hsh.entity.Personnel;
import com.hsh.entity.User;

public class PersonnelDao extends BaseDao {
	
	//查询所有
	public List<Personnel> queryAll() throws ClassNotFoundException, SQLException{
		Connection conn = getConn();
		String sql = "select p1.id,p1.uid,u1.username,p1.pid,p2.pName,p1.create,p1.createBy,"
				+ "u2.username,p1.status,p1.modifyTime from personnel p1,project p2,"
				+ "user u1,user u2 where p1.pid = p2.pid and u1.uid = p1.uid and u2.uid = p1.createBy;";
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Personnel> list = new ArrayList<>();
		while(resultSet.next()){
			Personnel p = new Personnel();
			p.setId(resultSet.getInt(1));
			p.setUid(resultSet.getInt(2));
			p.setuName(resultSet.getString(3));
			p.setPid(resultSet.getInt(4));
			p.setpName(resultSet.getString(5));
			p.setCreate(resultSet.getString(6));
			p.setCreateBy(resultSet.getInt(7));
			p.setCreateByName(resultSet.getString(8));
			p.setStatus(resultSet.getString(9));
			p.setModifyTime(resultSet.getString(10));
			list.add(p);
		}
		//关闭（倒关）
		resultSet.close();
		statement.close();
		conn.close();
		return list;
	}
	
	public List<Personnel> queryByPId(int id) throws ClassNotFoundException, SQLException{
		Connection conn = getConn();
		String sql = "select p1.id,p1.uid,u1.username,p1.pid,p2.pName,p1.create,p1.createBy,"
				+ "u2.username,p1.status,p1.modifyTime from personnel p1,project p2,"
				+ "user u1,user u2 where p1.pid = p2.pid and u1.uid = p1.uid and u2.uid = p1.createBy and p2.pid = "
				+id+" ;";
		System.out.println("queryById "+sql);
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Personnel> list = new ArrayList<>();
		while(resultSet.next()){
			Personnel p = new Personnel();
			p.setId(resultSet.getInt(1));
			p.setUid(resultSet.getInt(2));
			p.setuName(resultSet.getString(3));
			p.setPid(resultSet.getInt(4));
			p.setpName(resultSet.getString(5));
			p.setCreate(resultSet.getString(6));
			p.setCreateBy(resultSet.getInt(7));
			p.setCreateByName(resultSet.getString(8));
			p.setStatus(resultSet.getString(9));
			p.setModifyTime(resultSet.getString(10));
			list.add(p);
		}
		//关闭（倒关）
		resultSet.close();
		statement.close();
		conn.close();
		return list;
	}
	
	
	
	//insert
	public int insert(Personnel personnel) throws ClassNotFoundException, SQLException{
		Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into personnel (uid,pid,`create`,createBy,`status`,modifyTime) values(?,?,?,?,?,?)";
	    System.out.println(sql);
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1, personnel.getUid());
	        pstmt.setInt(2, personnel.getPid());
	        pstmt.setString(3, personnel.getCreate());
	        pstmt.setInt(4, personnel.getCreateBy());
	        pstmt.setString(5, personnel.getStatus());
	        pstmt.setString(6, personnel.getModifyTime());
	        System.out.println(pstmt.toString());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}

	//更新
		public void updateStatuts(int id,String status) throws ClassNotFoundException, SQLException{
			Connection conn = getConn();
			SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sql = "update personnel set `status` = '"+status+"' , modifyTime = '"+sft.format(new Date())+"' where uid = "+id;
			System.out.println(sql);
			PreparedStatement pstmt;
		    try {
		        pstmt = (PreparedStatement) conn.prepareStatement(sql);
		        pstmt.executeUpdate();
		        pstmt.close();
		        conn.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}	
		
		//根据id删除
		public void deleteById(int id) throws ClassNotFoundException, SQLException{
			Connection conn = getConn();
			String sql = "delete from personnel where id = "+id;
			PreparedStatement pstmt;
		    try {
		        pstmt = (PreparedStatement) conn.prepareStatement(sql);
		        pstmt.executeUpdate();
		        pstmt.close();
		        conn.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		
		public List<User> queryUserNotInProject(String pid) throws ClassNotFoundException, SQLException{
			Connection conn = getConn();
			String sql = "SELECT * FROM USER u "
					+ "WHERE u.uid NOT IN (SELECT p.uid FROM personnel p WHERE p.pid="+pid+") "
					+ "AND u.uid NOT IN (SELECT p1.headerId FROM project p1 WHERE p1.pid="+pid+") "
					+ "AND u.permission !=1";
			System.out.println("queryById "+sql);
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			List<User> list = new ArrayList<>();
			while(resultSet.next()){
				User user = new User();
				user.setuId(resultSet.getString(1));
				user.setUsername(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setPermission(resultSet.getInt(4));
				list.add(user);
			}
			//关闭（倒关）
			resultSet.close();
			statement.close();
			conn.close();
			return list;
		}
}
