package cn.heapstack.simpleblog.dao;

import java.util.List;

import cn.heapstack.simpleblog.domain.User;

public interface IUserDAO {

	boolean checkExist(String userId);
	
	boolean addUser(User u);
	
	User getUser(String userId);
	
	boolean updateUser(User u);
	
	boolean deleteUser(String userId);
	
	List<User> queryAll();
	
	List<User> queryByName(String nickName);
}
