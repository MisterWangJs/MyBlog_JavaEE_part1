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
import dao.IBlogDao;
import dao.impl.BlogDaoJDBCImpl;
import entity.Blog;

/**
 * Servlet implementation class GuestBookAddServlet
 */
@WebServlet("/blog_add")
public class BlogAddServlet extends HttpServlet {
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					       
			        response.setContentType("application/json; charset=utf-8");			 
			      //1.设置请求参数的编码
					request.setCharacterEncoding("utf8");
					//2.得到请求参数
					Date date=new Date();
					  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
					String pubtime=format.format(date);
					
					String blog_title = request.getParameter("blog_title");
					String blog_content = request.getParameter("blog_content");
					String author = request.getParameter("author");
					String blog_type = request.getParameter("blog_type");
					
					//3.创建实体对象
					Blog blog = new Blog();
					blog.setBlog_title(blog_title);
					blog.setBlog_content(blog_content);
					blog.setPubtime(pubtime);
					blog.setAuthor(author);
					blog.setBlog_type(blog_type);
					
					//4.借助IGuestBookDao将信息添加到DB中
					IBlogDao blogDao = new BlogDaoJDBCImpl();
					
					int i = blogDao.add(blog);
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
