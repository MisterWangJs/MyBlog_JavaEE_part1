package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import entity.ResultInfo;
import dao.IBlogDao;
import dao.impl.BlogDaoJDBCImpl;

@WebServlet("/blog_delete")
public class BlogDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
		
    	String id = request.getParameter("bid");
		int bid = Integer.parseInt(id);
		
	
		IBlogDao blogDao = new BlogDaoJDBCImpl();
		
		int i = blogDao.deleteByBId(bid);
	
		if(i == 1){
				 Gson gson = new Gson();
				 String result = gson.toJson(new ResultInfo(1, "删除成功！"));
				 response.getWriter().write(result);
		       
			}
	         else {
	        	 Gson gson = new Gson();
	             String result = gson.toJson(new ResultInfo(0, "删除失败！"));
	             response.getWriter().write(result);
	        }
	}
}
