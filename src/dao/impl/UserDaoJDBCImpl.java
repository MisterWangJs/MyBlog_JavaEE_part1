package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dao.IUserDao;
import entity.user;
import util.JDBCUtil;



public class UserDaoJDBCImpl implements IUserDao {

	@Override
	public int add(user user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int n = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into user (userName,password)values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			n = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	@Override
	public user findByName(String userName) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		user user = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from user where userName = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			

			if(rs != null){
				while(rs.next()){
					user = new user();
					user.setUserName(rs.getString("userName"));
					user.setPassword(rs.getString("password"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	
	}

	@Override
	public int ModifyUserPsswortd(String userName, user user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int n = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update user set password=? where userName=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getUserName());		
			n = ps.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
		
		
	
	}

	

}
