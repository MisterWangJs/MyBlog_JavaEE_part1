package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.io.IOException;

import dao.IUserDao;
import dao.impl.UserDaoJDBCImpl;
import entity.ResultInfo;
import entity.user;

@WebServlet("/register")
public class ResigerServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 	request.setCharacterEncoding("UTF-8");
	       
	        response.setContentType("application/json; charset=utf-8");
	       
	        String userName = request.getParameter("userName");
	       
	        String password = request.getParameter("password");
	        
		//5.借助DAO把实体对象添加到DB?
		IUserDao  userDao =  new UserDaoJDBCImpl();
		
		user u =null;
		try{
			u = userDao.findByName(userName);
			Gson gson = new Gson();
			if(!"".equals(u.getUserName())){
				String result = gson.toJson(new ResultInfo(3, "此用户名已存在！"));
	            response.getWriter().write(result);
				return;
			}
			
		}catch(NullPointerException e){
			u = new user();
			u.setUserName(userName);
			u.setPassword(password);
	
			 int i = userDao.add(u);
			 
			 Gson gson = new Gson();
		      
			 
			 	if(i==1){
					 String result = gson.toJson(new ResultInfo(1, "注册成功！"));
			            response.getWriter().write(result);
				}
		        else {
		            
		            String result = gson.toJson(new ResultInfo(0, "注册失败！"));
		            response.getWriter().write(result);
		        }
				
				
			
		}
		
		
		
		
	}
}