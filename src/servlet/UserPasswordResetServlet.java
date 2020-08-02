package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dao.IUserDao;
import dao.impl.UserDaoJDBCImpl;
import entity.ResultInfo;
import entity.user;

/**
 * Servlet implementation class UserPasswordResetServlet
 */
@WebServlet("/password_reset")
public class UserPasswordResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPasswordResetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");	       
        response.setContentType("application/json; charset=utf-8");      
	
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		IUserDao userDao = new UserDaoJDBCImpl();
		
		user use = userDao.findByName(userName);
		if(use.getPassword().equals(password)){
			user user = new user();
			user.setUserName(userName);
			user.setPassword(password1);
			
			int i = userDao.ModifyUserPsswortd(userName, user);
			if(i == 1){
				 Gson gson = new Gson();
				 String result = gson.toJson(new ResultInfo(1, "修改成功！"));
				 response.getWriter().write(result);      
			}
	        else {
	       	 	Gson gson = new Gson();
	            String result = gson.toJson(new ResultInfo(0, "修改失败！"));
	            response.getWriter().write(result);
	       }
			
		}
		else{
			Gson gson = new Gson();
            String result = gson.toJson(new ResultInfo(0, "旧密码输入错误！"));
            response.getWriter().write(result);
		}
	}
}
