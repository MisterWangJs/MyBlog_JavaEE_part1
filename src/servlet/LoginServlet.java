package servlet;
import com.google.gson.Gson;
import entity.ResultInfo;
import dao.IUserDao;
import dao.impl.UserDaoJDBCImpl;
import entity.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	       
	        response.setContentType("application/json; charset=utf-8");
	       
	        String userName = request.getParameter("userName");
	       
	        String password = request.getParameter("password");
	        
	     
			IUserDao userDao = new UserDaoJDBCImpl();
			user loginUser = userDao.findByName(userName);
				if(loginUser==null){
					Gson gson = new Gson();
					String result = gson.toJson(new ResultInfo(0, "登录失败！"));
		            response.getWriter().write(result);
				}else{
					  Gson gson = new Gson();
				    
				        if(loginUser.getUserName().equals(userName)&&loginUser.getPassword().equals(password)){
							 String result = gson.toJson(new ResultInfo(1, "登陆成功！"));
					            response.getWriter().write(result);
						}
				        else if(!loginUser.getPassword().equals(password)) {
				            String result = gson.toJson(new ResultInfo(0, "登录失败！"));
				            response.getWriter().write(result);
				        }
				}
			
	       
	      
	        
	        
	        
	    }
}