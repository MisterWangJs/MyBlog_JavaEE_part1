package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ISayDao;
import dao.impl.SayDaoJDBCImpl;
import entity.ResultInfo;

@WebServlet("/say_delete")
public class SayingDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
		
		//1.获得请求参数
		String id = request.getParameter("sid");
		int sid = Integer.parseInt(id);
		
		//2.借助DAO实现删除的业务逻辑
		ISayDao sayDao = new SayDaoJDBCImpl();
		
		int i = sayDao.deleteBySId(sid);
		
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
