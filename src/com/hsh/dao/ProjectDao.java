package com.hsh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hsh.entity.Project1;
import com.opensymphony.xwork2.ActionContext;

public class ProjectDao extends BaseDao {
	// 添加project对象
	public int insert(Project1 project) throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into project (pName,`create`,createBy,headerId,`status`,modifyTime) values(?,?,?,?,?,?)";
		System.out.println(sql);
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, project.getpName());
			pstmt.setString(2, project.getCreate());
			pstmt.setInt(3, project.getCreateBy());
			pstmt.setInt(4, project.getHeadId());
			pstmt.setString(5, project.getStatus());
			pstmt.setString(6, project.getModifyTime());
			System.out.println(pstmt.toString());
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public Project1 queryById(String pid)throws Exception{
		Connection conn = getConn();
		String sql = "select p.*,u1.username,u2.username from project p " + "left join user u1 on u1.uid = p.createBy "
				+ "left join user u2 on u2.uid = p.headerId where p.pid = "+pid;
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
		return list.get(0);
	}

	// 查询所有project
	public List<Project1> queryAll() throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		String sql = "select p.*,u1.username,u2.username from project p " + "left join user u1 on u1.uid = p.createBy "
				+ "left join user u2 on u2.uid = p.headerId;";
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

	// 根据项目名查询
	public List<Project1> queryByPName(String pName) throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		String sql = "select p.*,u1.username,u2.username from project p " + "left join user u1 on u1.uid = p.createBy "
				+ "left join user u2 on u2.uid = p.headerId" + " where p.pName like '%" + pName + "%';";
		Statement statement = conn.createStatement();
		System.out.println(sql);
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

	
	
	// 根据负责人查询
	public List<Project1> queryByHeaderId(String headerId) throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		String sql = "select p.*,u1.username,u2.username from project p " + "left join user u1 on u1.uid = p.createBy "
				+ "left join user u2 on u2.uid = p.headerId" + " where p.headerId = " + headerId + ";";
		Statement statement = conn.createStatement();
		System.out.println(sql);
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

	// 根据id删除
	public void deleteById(int id) throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		String sql = "delete from project where pid = " + id;
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

	// 更新
	public void updateById(int id,String pName,String status, String headId) throws ClassNotFoundException, SQLException {
		
		Connection conn = getConn();
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "update project set headerId = " + headId + " , modifyTime = '" + sft.format(new Date())
				+"' ,status = '"+status + "' ,pName = '"+pName
				+ "' where pid = " + id;
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


}
