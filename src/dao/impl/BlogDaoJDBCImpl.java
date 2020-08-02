package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dao.IBlogDao;
import entity.Blog;
import util.JDBCUtil;



public class BlogDaoJDBCImpl implements IBlogDao {

	@Override
	public int add(Blog blog) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int n = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into blog (blog_title,blog_content,pubtime,author,blog_type)values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, blog.getBlog_title());
			ps.setString(2, blog.getBlog_content());
			ps.setString(3, blog.getPubtime());
			ps.setString(4, blog.getAuthor());
			ps.setString(5, blog.getBlog_type());
						
			n = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	

	@Override
	public List<Blog> findAll() {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Blog> blogList = null;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from blog";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			

			if(rs != null){
				boolean flag = true;
				while(rs.next()){
					if(flag){
						blogList = new ArrayList<Blog>();
						flag = false;
					}
					Blog blog = new Blog();
					blog.setBid(rs.getInt("bid"));
					blog.setBlog_title(rs.getString("blog_title"));
					blog.setBlog_content(rs.getString("blog_content"));
					blog.setPubtime(rs.getString("pubtime"));
					blog.setAuthor(rs.getString("author"));
					blog.setBlog_type(rs.getString("blog_type"));
					blogList.add(blog);
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return blogList;
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
			String sql = "select count(*) as totalcount from blog";
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

	public List<Blog> findOnePage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Blog> blogList = null;
		

		int startRecordNo = (pageIndex - 1) * pageSize;
		
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from blog order by bid desc limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, startRecordNo);
			ps.setInt(2, pageSize);
			
			rs = ps.executeQuery();
			

			if(rs != null){
				boolean flag = true;
				while(rs.next()){
					if(flag){
						blogList = new ArrayList<Blog>();
						flag = false;
					}
					
					Blog blog = new Blog();
					blog.setBid(rs.getInt("bid"));
					blog.setBlog_title(rs.getString("blog_title"));
					blog.setBlog_content(rs.getString("blog_content"));
					blog.setPubtime(rs.getString("pubtime"));
					blog.setAuthor(rs.getString("author"));
					blog.setBlog_type(rs.getString("blog_type"));
					blogList.add(blog);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return blogList;
	}

	@Override
	public int deleteByBId(int bid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int n = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from blog where bid = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			
			n = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	
	
	public int ModifyBlog(int bid, Blog blog) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int n = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update blog set blog_title=?,blog_content=?,pubtime=?,author=?,blog_type=? where bid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, blog.getBlog_title());
			ps.setString(2, blog.getBlog_content());
			ps.setString(3, blog.getPubtime());
			ps.setString(4, blog.getAuthor());
			ps.setString(5, blog.getBlog_type());
			ps.setInt(6, bid);
			
			
			n = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}


}
