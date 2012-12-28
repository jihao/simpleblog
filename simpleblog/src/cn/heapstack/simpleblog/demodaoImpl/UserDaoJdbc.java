package cn.heapstack.simpleblog.demodaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.simpleblog.domain.User;
import cn.heapstack.simpleblog.dao.IUserDAO;

public class UserDaoJdbc implements IUserDAO {
	
	private static Logger logger = LoggerFactory.getLogger(UserDaoJdbc.class);
	private DataSource dataSource;

	/**
	 * 
	 */
	public boolean addUser(User u) {
		int result = -1;
		Connection conn = null;
		PreparedStatement ps = null;

		try
		{
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT into USERS(username,password) values(?,?)");
			ps.setString(1, u.getUserId());
			ps.setString(2, u.getPassword());
			logger.debug("INSERT into USERS(username,password) values(?,?)");
			result = ps.executeUpdate();
			if(result<=0)
				return false;
			ps = conn.prepareStatement("INSERT into USERS_INFO(firstname,lastname,address,zipcode,mobilephone,info,id) " +
					"values(?,?,?,?,?,?,(select id from USERS where username=?));");
			logger.debug("INSERT into USERS_INFO(firstname,lastname,address,zipcode,mobilephone,info,id) " +
					"values(?,?,?,?,?,?,(select id from USERS where username=?));");
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(ps!=null){
				try
				{
					ps.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return result>0?true:false;
	}

	public boolean deleteUser(String username) {
		int result = -1;
		Connection conn = null;
		PreparedStatement ps = null;

		try
		{
			conn = dataSource.getConnection();
			logger.debug("delete from USERS where username=?");
			ps = conn.prepareStatement("delete from USERS where username=?");
			ps.setString(1, username);
			result = ps.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(ps!=null){
				try
				{
					ps.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return result>0?true:false;
	}

	public List<User> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> queryByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateUser(User u) {
		int result = -1;
		Connection conn = null;
		PreparedStatement ps = null;

		try
		{
			conn = dataSource.getConnection();
			logger.debug("update USERS set password=? where username=?");
			ps = conn.prepareStatement("update USERS set password=? where username=?");
			ps.setString(1, u.getPassword());
			ps.setString(2, u.getUserId());
			
			result = ps.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(ps!=null){
				try
				{
					ps.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return result>0?true:false;
	}
	

	/**
	 * ����û���ȡ���û���ID
	 */
	public int getUID(String username) {
		int id = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = dataSource.getConnection();
			logger.debug("SELECT id from USERS where username=?");
			ps = conn.prepareStatement("SELECT id from USERS where username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs!=null)
			{
				if(rs.next())
					id = rs.getInt("ID");
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(ps!=null){
				try
				{
					ps.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return id;
	}

	public boolean checkExist(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		try
		{
			conn = dataSource.getConnection();
			logger.debug("SELECT * from USERS where username=?");
			ps = conn.prepareStatement("SELECT * from USERS where username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs!=null)
			{
				if(rs.next())
					result = true;
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(ps!=null){
				try
				{
					ps.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean deleteUser(int uid) {
		int result = -1;
		Connection conn = null;
		PreparedStatement ps = null;

		try
		{
			conn = dataSource.getConnection();
			logger.debug("delete from USERS where id=?");
			ps = conn.prepareStatement("delete from USERS where id=?");
			ps.setInt(1, uid);
			result = ps.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(ps!=null){
				try
				{
					ps.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return result>0?true:false;
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
