package cn.heapstack.simpleblog.demodaoImpl;

import java.util.HashMap;
import java.util.List;

import cn.heapstack.simpleblog.dao.IUserDAO;
import cn.heapstack.simpleblog.domain.User;

public class UserDAODemo implements IUserDAO{

	public static HashMap<String, User> userMap = new HashMap<String, User>();
	static
	{
		for(int i=0;i<5;i++)
		{
			User u = new User();
			u.setUserId("uid"+i);
			u.setPassword("uid"+i);
			u.setEmail("uid"+i+"@email.com");
			u.setNickName("NickNameUid"+i);
			userMap.put("uid"+i, u);
		}
		User jihao = new User();
		jihao.setUserId("jihao");
		jihao.setPassword("jihao");
		jihao.setEmail("jihao@heapstack.cn");
		jihao.setNickName("浩");
		userMap.put("jihao", jihao);
	}
		
	@Override
	public User getUser(String uId)
	{
		return userMap.get(uId);
	}

	@Override
	public boolean addUser(User u) {
		userMap.put(u.getUserId(), u);
		return true;
	}

	@Override
	public boolean checkExist(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> queryByName(String nickName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}
}
