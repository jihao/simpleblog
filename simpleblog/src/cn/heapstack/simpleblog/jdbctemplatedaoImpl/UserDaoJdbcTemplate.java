package cn.heapstack.simpleblog.jdbctemplatedaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.heapstack.simpleblog.dao.IUserDAO;
import cn.heapstack.simpleblog.domain.User;

public class UserDaoJdbcTemplate extends JdbcDaoSupport implements
		IUserDAO {

	public boolean checkExist(String userID) {
		int i = getJdbcTemplate().queryForInt("SELECT COUNT(*) FROM USER WHERE userID = ?", new Object[]{userID});
		return i>0;
	}
	
	public boolean addUser(User u) {
		int i = getJdbcTemplate().update("INSERT INTO USER (userID, password, email, nickname) VALUES (?, ?, ?, ?)", 
				new Object[]{u.getUserId(),u.getPassword(),u.getEmail(),u.getNickName()});
		return i>0;
	}

	public User getUser(String userID) {
		return (User) getJdbcTemplate().queryForObject("SELECT * FROM USER WHERE userID = ?", new Object[]{userID}, new UserRowMapper());
	}
	
	public boolean updateUser(User u) {
		int i = getJdbcTemplate().update("UPDATE USER set password=? WHERE userID=?",new Object[]{u.getPassword(),u.getUserId()});
		return i>0;
	}
	
	public boolean deleteUser(String userID) {
		int i = getJdbcTemplate().update("DELETE FROM USER WHERE userID=?",new Object[]{userID});
		return i>0;
	}

	@SuppressWarnings("unchecked")
	public List<User> queryAll() {
		return getJdbcTemplate().query("SELECT * FROM USER",
				new UserRowMapper()
		);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> queryByName(String name) {
		return getJdbcTemplate().query("SELECT * FROM USER WHERE userID LIKE '%?%' OR nickName LIKE '%?%' ",
				new Object[]{name,name},
				new UserRowMapper()
		);
	}
	
}

class UserRowMapper implements RowMapper
{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		User u = new User();
		u.setUserId(rs.getString("userID"));
		u.setPassword(rs.getString("password"));
		u.setEmail(rs.getString("email"));
		u.setNickName(rs.getString("nickname"));
		
		return u;
	}
}
