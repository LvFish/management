package com.hsh.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import com.hsh.entity.Project;

public class ProjectDao extends BaseDao{
	public int insert(Project project) throws ClassNotFoundException, SQLException{
		Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into project (pName,create,createBy,headerId,status,modifyTime) values(?,?,?,?,?,?)";
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
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
}
