package cn.heapstack.simpleblog.jdbctemplatedaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.heapstack.simpleblog.dao.ICommentsDAO;
import cn.heapstack.simpleblog.domain.Comments;

public class CommentsDaoJdbcTemplate extends JdbcDaoSupport implements
		ICommentsDAO {

	@Override
	public boolean addComments(Comments c) {
		int i = getJdbcTemplate().update(
				"INSERT INTO comments (commentsID, theCommentsArticleID, content, homePageURL, postDate, title, userEmail, userName)" +
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] { c.getCommentsID(), c.getTheCommentsArticleID(), c.getContent(), c.getHomePageURL(), c.getPostDate(), c.getTitle(), c.getUserEmail(), c.getUserName() },
				new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR });
		return i == 1;
	}

	@Override
	public boolean deleteComments(String commentsID) {
		int i = getJdbcTemplate().update(
				"DELETE FROM comments where commentsID = ?", 
				new Object[] { commentsID },
				new int[] { Types.VARCHAR });
		return i == 1;

	}

	@Override
	public Comments getComments(String commentsID) {
		return (Comments) getJdbcTemplate().queryForObject(
				"select * FROM comments where commentsID = ?", 
				new Object[] { commentsID },
				new int[] { Types.VARCHAR }, 
				new CommentsRowMapper());
	}
	
	@Override
	public boolean updateComments(Comments c) {
		int i = getJdbcTemplate().update(
				"update comments set theCommentsArticleID = ? , content = ?, homePageURL = ?, postDate = ?, title = ?, userEmail = ?, userName = ?" +
				"where commentsID = ?",
				new Object[] { c.getTheCommentsArticleID(), c.getContent(), c.getHomePageURL(), c.getPostDate(), c.getTitle(), c.getUserEmail(), c.getUserName(), c.getCommentsID() },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR , Types.VARCHAR});
		return i == 1;
	}
	
	@Override
	public List<Comments> queryAll() {
		return getJdbcTemplate().query(
				"select * FROM comments ", 
				new CommentsRowMapper());
	}

	@Override
	public List<Comments> queryByArticleID(String articleID) {
		return getJdbcTemplate().query(
				"select * FROM comments where theCommentsArticleID = ?", 
				new Object[] { articleID },
				new int[] { Types.VARCHAR }, 
				new CommentsRowMapper());
	}



	class CommentsRowMapper implements RowMapper
	{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Comments c  = new Comments();
			c.setCommentsID(rs.getString("commentsID"));
			c.setContent(rs.getString("content"));
			c.setHomePageURL(rs.getString("homePageURL"));
			c.setPostDate(rs.getDate("postDate"));
			c.setTheCommentsArticleID(rs.getString("theCommentsArticleID"));
			c.setTitle(rs.getString("title"));
			c.setUserName(rs.getString("userName"));
			c.setUserEmail(rs.getString("userEmail"));
			return c;
		}
		
	}
}
