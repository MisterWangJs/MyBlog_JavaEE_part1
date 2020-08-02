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
import entity.Saying;

@WebServlet("/say_modify")
public class SayingModifyServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json; charset=utf-8");
		
		String id = request.getParameter("sid");
		String saycontent = request.getParameter("saycontent");
		String saytime = request.getParameter("saytime");

		int sid = Integer.parseInt(id);
		
		
		
		//3.创建实体对象
		Saying say = new Saying();
		say.setSid(sid);
		say.setSaycontent(saycontent);
		say.setSaytime(saytime);
		
		//4.借助ISayDao将信息添加到DB中
		ISayDao sayDao = new SayDaoJDBCImpl();
		int i = sayDao.ModifySay(sid, say);
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
}
