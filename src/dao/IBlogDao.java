package dao;

import java.util.List;

import entity.Blog;


public interface IBlogDao {

	int add(Blog blog);
	
	int deleteByBId(int bid);

	public int ModifyBlog(int bid, Blog blog);

	List<Blog> findAll();

	int findTotalCount();
	
	public List<Blog> findOnePage(int pageIndex, int pageSize);

	

	


}
