package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dao.ISayDao;
import entity.Saying;
import util.JDBCUtil;



public class SayDaoJDBCImpl implements ISayDao {

	@Override
	public int add(Saying say) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int n = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into saying (saycontent,saytime)values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, say.getSaycontent());
			ps.setString(2, say.getSaytime());
						
			n = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	

	@Override
	public List<Saying> findAll() {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Saying> sayList = null;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from saying";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			

			if(rs != null){
				boolean flag = true;
				while(rs.next()){
					if(flag){
						sayList = new ArrayList<Saying>();
						flag = false;
					}
					Saying say = new Saying();
					say.setSid(rs.getInt("sid"));
					say.setSaycontent(rs.getString("saycontent"));
					say.setSaytime(rs.getString("saytime"));
					sayList.add(say);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sayList;
	}

	@Override
	public int findTotalCount() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int n = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select count(*) as totalcount from saying";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			if(rs != null){
				while(rs.next()){
					n = rs.getInt("totalcount");
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}

	public List<Saying> findOnePage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Saying> sayList = null;
		

		int startRecordNo = (pageIndex - 1) * pageSize;
		
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from saying order by sid desc limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, startRecordNo);
			ps.setInt(2, pageSize);
			
			rs = ps.executeQuery();
			

			if(rs != null){
				boolean flag = true;
				while(rs.next()){
					if(flag){
						sayList = new ArrayList<Saying>();
						flag = false;
					}
					Saying say = new Saying();
					say.setSid(rs.getInt("sid"));
					say.setSaycontent(rs.getString("saycontent"));
					say.setSaytime(rs.getString("saytime"));
					sayList.add(say);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sayList;
	}

	@Override
	public int deleteBySId(int sid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int n = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from saying where sid = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sid);
			
			n = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	
	
	public int ModifySay(int sid, Saying say) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int n = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update saying set saycontent=?,saytime=? where sid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, say.getSaycontent());
			ps.setString(2, say.getSaytime());
			ps.setInt(3, sid);
			
			
			n = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}

}
