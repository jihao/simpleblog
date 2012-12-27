package cn.heapstack.simpleblog.jdbctemplatedaoImpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IArticleCategoryDAO;
import cn.heapstack.simpleblog.dao.IArticleDAO;
import cn.heapstack.simpleblog.dao.IArticleTagDAO;
import cn.heapstack.simpleblog.domain.Article;
import cn.heapstack.simpleblog.domain.Category;
import cn.heapstack.simpleblog.domain.Comments;
import cn.heapstack.simpleblog.domain.Tag;
import cn.heapstack.simpleblog.domain.User;

public class ArticleCategoryDaoJdbcTemplate extends JdbcDaoSupport implements
		IArticleCategoryDAO {

	@Override
	public boolean addRecord(String articleID, String categoryName) {
		int i = getJdbcTemplate().update("insert into article_category (articleID, categoryName) values(?, ?)", 
				new Object[] { articleID, categoryName}, 
				new int[] { Types.VARCHAR, Types.VARCHAR});
		return i > 0;
	}

	@Override
	public boolean removeRecordByArticleID(String articleID) {
		int i = getJdbcTemplate().update("delete from article_category where articleID = ?", 
				new Object[]{articleID}, 
				new int[]{Types.VARCHAR});
		return i > 0;
	}

	@Override
	public boolean removeRecordByCategoryName(String categoryName) {
		int i = getJdbcTemplate().update("delete from article_category where categoryName = ?", 
				new Object[]{categoryName}, 
				new int[]{Types.VARCHAR});
		return i > 0;
	}

	@Override
	public List<Article> searchArticlesByCategoryName(String categoryName) {
		List<Article> result =  getJdbcTemplate().query("select a.articleID, a.asDraft, " +
				"a.userID, a.title, " +
				"a.content, a.postDate, a.staticLinkURL, a.setTop " +
				"from article a, article_category a_c " +
				"where a.articleID = a_c.articleID and a_c.categoryName = ?", 
				new Object[] { categoryName }, 
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
	public List<Category> getCategoryListByArticleID(String articleID) {
		return getJdbcTemplate().query("select c.categoryName, c.categoryName,c.categoryComments, c.relatedPosts " +
				"from category c, article_category a_c " +
				"where c.categoryName=a_c.categoryName and a_c.articleID=?",
				new Object[] { articleID },new int[] { Types.VARCHAR },
				new CategoryRowMapper());
	}

	@Override
	public int getArticleCountsRelatedWithCategoryName(String categoryName) {
		return getJdbcTemplate().queryForInt("SELECT count(*) FROM article_category a_c WHERE a_c.categoryName = ?",
				new Object[] { categoryName });
	}

}
