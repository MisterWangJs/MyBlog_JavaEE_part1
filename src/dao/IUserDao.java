package dao;

import java.util.List;


import entity.user;

public interface IUserDao {


	public int add(user user);

	public user findByName(String userName);

	public int ModifyUserPsswortd(String userName, user user);
	
}
