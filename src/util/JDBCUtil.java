package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	private static String url = "jdbc:mysql://localhost:3306/my_blog?useUnicode=true&characterEncoding=utf8";
	private static String user = "root";
	private static String password = "548748264..ls";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(ResultSet rs,Statement st,Connection conn){
		try {
			if(rs != null){
				rs.close();
			}			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null){
					st.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try{
					if(conn != null){
						conn.close();
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
