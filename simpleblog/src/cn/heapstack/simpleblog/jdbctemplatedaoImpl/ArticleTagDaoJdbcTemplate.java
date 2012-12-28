package cn.heapstack.simpleblog.jdbctemplatedaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IArticleDAO;
import cn.heapstack.simpleblog.dao.IArticleTagDAO;
import cn.heapstack.simpleblog.domain.Article;
import cn.heapstack.simpleblog.domain.Tag;

public class ArticleTagDaoJdbcTemplate extends JdbcDaoSupport implements
		IArticleTagDAO {

	@Override
	public boolean addRecord(String articleID, String tagName) {
		int i = getJdbcTemplate().update("INSERT INTO article_tag(articleID,tagName) values(?,?)", 
				new Object[] { articleID, tagName }, 
				new int[] { Types.VARCHAR, Types.VARCHAR });
		return i > 0;
	}

	@Override
	public List<String> getTagNamesRelatedWithArticleID(String articleID) {
		return getJdbcTemplate().queryForList("SELECT tagName FROM article_tag WHERE articleID = ?", 
				new Object[]{articleID}, 
				new int[]{Types.VARCHAR}, 
				String.class);
	}

	@Override
	public boolean removeRecord(String articleID, String tagName) {
		int i = getJdbcTemplate().update("DELETE FROM article_tag WHERE articleID = ? and tagName = ?", 
				new Object[] { articleID, tagName}, 
				new int[] { Types.VARCHAR, Types.VARCHAR});
		return i > 0;
	}

	@Override
	public boolean removeRecordByArticleID(String articleID) {
		int i = getJdbcTemplate().update("DELETE FROM article_tag WHERE articleID = ?", 
				new Object[] { articleID }, 
				new int[] { Types.VARCHAR });
		return i > 0;
	}

	@Override
	public boolean removeRecordByTagName(String tagName) {
		int i = getJdbcTemplate().update("DELETE FROM article_tag WHERE tagName = ?", 
				new Object[]{ tagName}, 
				new int[]{Types.VARCHAR});
		return i > 0;
	}

	@Override
	public List<Tag> getTagListByArticleID(String articleID) {
		return getJdbcTemplate().query("SELECT t.tagName, t.tagName, t.relatedPosts " +
				"FROM tag t, article_tag a_t WHERE t.tagName=a_t.tagName and a_t.articleID = ?",
				new Object[] { articleID },
				new int[] { Types.VARCHAR },
				new TagRowMapper());
	}
	
	@Override
	public List<Article> searchArticlesByTagName(String tagName) {
		List<Article> result =  getJdbcTemplate().query("SELECT a.articleID, a.asDraft, " +
				"a.userID, a.title, " +
				"a.content, a.postDate, a.staticLinkURL, a.setTop " +
				"FROM article a, article_tag a_t " +
				"WHERE a.articleID=a_t.articleID and a_t.tagName = ?", 
				new Object[] { tagName }, 
				new int[] { Types.VARCHAR }, 
			 	new ArticleRowMapper() );
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		IArticleDAO articleDAO = daoFactory.getArticleDAO();
		
		for (Article article : result) {
			articleDAO.fillArticleBeanInfo(article);
		}
		
		return result;
	}

	@Override
	public int getArticleCountsRelatedWithTagName(String tagName) {
		return getJdbcTemplate().queryForInt("SELECT count(*) FROM article_tag a_t WHERE a_t.tagName = ?",
				new Object[] { tagName });
	}
}

class ArticleRowMapper implements RowMapper
{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Article a = new Article();			
		a.setArticleID(rs.getString("articleID"));
		a.setTitle(rs.getString("title"));
		a.setAsDraft(rs.getBoolean("asDraft"));
		a.setContent(rs.getString("content"));
		a.setPostDate(rs.getTimestamp("postDate"));
		a.setStaticLinkURL(rs.getString("staticLinkURL"));
		a.setSetTop(rs.getBoolean("setTop"));
		
		return a;
	}
}