package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import entity.ResultInfo;
import entity.Saying;
import dao.impl.SayDaoJDBCImpl;
import dao.ISayDao;
/**
 * Servlet implementation class GuestBookAddServlet
 */
@WebServlet("/say_add")
public class SayingAddServlet extends HttpServlet {
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
        
        			response.setContentType("application/json; charset=utf-8");		
					//1.设置请求参数的编码
					request.setCharacterEncoding("utf8");
					//2.得到请求参数
					Date date=new Date();
					DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
					String saytime=format.format(date);
					String saycontent = request.getParameter("saycontent");
					//3.创建实体对象
					Saying say = new Saying();
					say.setSaycontent(saycontent);
					say.setSaytime(saytime);
					//4.借助IGuestBookDao将信息添加到DB中
					ISayDao sayDao = new SayDaoJDBCImpl();
					
					int i = sayDao.add(say);
					if(i == 1){
						 Gson gson = new Gson();
						 String result = gson.toJson(new ResultInfo(1, "添加成功！"));
						 response.getWriter().write(result);
				       
					}
			        else {
			       	 Gson gson = new Gson();
			            String result = gson.toJson(new ResultInfo(0, "添加失败！"));
			            response.getWriter().write(result);
			       }	
				
		
		
		
	}
    

}
