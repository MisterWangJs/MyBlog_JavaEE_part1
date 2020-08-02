package dao;

import java.util.List;


import entity.Saying;

public interface ISayDao {

	int add(Saying say);

	int deleteBySId(int sid);

	public int ModifySay(int sid, Saying say);
	
	List<Saying> findAll();

	int findTotalCount();

	List<Saying> findOnePage(int pageIndex, int pageSize);

	
	
	
}
