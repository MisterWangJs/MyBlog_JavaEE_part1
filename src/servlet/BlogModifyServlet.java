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
import entity.Blog;

@WebServlet("/blog_modify")
public class BlogModifyServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json; charset=utf-8");
        
		String id = request.getParameter("bid");
		String blog_title = request.getParameter("blog_title");
		String blog_content = request.getParameter("blog_content");		
		String pubtime = request.getParameter("pubtime");
		String author = request.getParameter("author");		
		String blog_type = request.getParameter("blog_type");
		int bid = Integer.parseInt(id);
		
		
		
		//3.创建实体对象
	
		Blog blog = new Blog();
		blog.setBlog_title(blog_title);
		blog.setBlog_content(blog_content);
		blog.setPubtime(pubtime);
		blog.setAuthor(author);
		blog.setBlog_type(blog_type);
		
		
		//4.借助ISayDao将信息添加到DB中
		IBlogDao blogDao = new BlogDaoJDBCImpl();
		int i = blogDao.ModifyBlog(bid, blog);
		
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
