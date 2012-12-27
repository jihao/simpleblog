package cn.heapstack.simpleblog.jdbctemplatedaoImpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IArticleCategoryDAO;
import cn.heapstack.simpleblog.dao.IArticleDAO;
import cn.heapstack.simpleblog.dao.IArticleTagDAO;
import cn.heapstack.simpleblog.dao.ICommentsDAO;
import cn.heapstack.simpleblog.domain.Article;
import cn.heapstack.simpleblog.domain.Category;
import cn.heapstack.simpleblog.domain.Comments;
import cn.heapstack.simpleblog.domain.Tag;
import cn.heapstack.simpleblog.domain.User;

public class ArticleDaoJdbcTemplate extends JdbcDaoSupport implements
		IArticleDAO {
	
	Logger logger = LoggerFactory.getLogger(ArticleDaoJdbcTemplate.class);
	
	@Override
	public boolean addArticle(Article e) {
		int i = getJdbcTemplate().update(
				"insert into article(articleID, asDraft, title, content, postDate, userID, staticLinkURL, setTop) values(?, ?, ?, ?, ?, ?, ?, ?)" , 
				new Object[] { e.getArticleID(), e.isAsDraft(), e.getTitle(), e.getContent(), e.getPostDate(), e.getAuthor().getUserId(), e.getStaticLinkURL(), e.isSetTop()}, 
				new int[] { Types.VARCHAR, Types.BOOLEAN, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR, Types.BOOLEAN});
		return i==1;
	}

	@Override
	public boolean isArticleExist(String articleID) {
		int i = getJdbcTemplate().queryForInt("SELECT count(*) FROM article WHERE articleID = ?" , 
				new Object[] { articleID }, 
				new int[] { Types.VARCHAR});
		return i==1;
	}


	@Override
	public Article getArticle(String articleID) {
		Article result = (Article) getJdbcTemplate().queryForObject("SELECT * FROM article WHERE articleID = ?", 
				new Object[] { articleID }, 
				new int[] { Types.VARCHAR},
				new ArticleRowMapper());
		fillArticleBeanInfo(result);
		
		return result;
	}

	@Override
	public boolean updateArticle(Article e) {
		int i = getJdbcTemplate().update("update article set asDraft = ?, title = ?, content = ?, postDate = ?, userID = ?, staticLinkURL = ?, setTop = ? WHERE articleID = ?", 
				new Object[] { e.isAsDraft(), e.getTitle(), e.getContent(), e.getPostDate(), e.getAuthor().getUserId(), e.getStaticLinkURL(), e.isSetTop(), e.getArticleID()},
				new int[] { Types.BOOLEAN, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR, Types.BOOLEAN, Types.VARCHAR} );
		return i==1;
	}
	
	@Override
	public boolean deleteArticle(String articleID) {
		int i = getJdbcTemplate().update("delete FROM article WHERE articleID = ?", 
				new Object[] { articleID }, 
				new int[] { Types.VARCHAR} );
		return i==1;
	}

	
	@Override
	public int getArticleTotalCount() {
		return getJdbcTemplate().queryForInt("SELECT count(*) FROM article");
	}

	@Override
	public List<Article> queryAll() {
		List<Article> result = getJdbcTemplate().query("SELECT * FROM article", 
				new ArticleRowMapper());
		for (Article article : result) {
			fillArticleBeanInfo(article);
		}
		return result;
	}

	@Override
	public List<Article> queryByName(String title) {
		List<Article> result = getJdbcTemplate().query("SELECT * FROM article WHERE title like %?%", 
				new Object[] { title }, 
				new int[] { Types.VARCHAR},
				new ArticleRowMapper());
		for (Article article : result) {
			fillArticleBeanInfo(article);
		}
		return result;
	}

	@Override
	public List<Article> queryByPage(int pageNumber, int pageSize) {
		// MYSQL Paging example
		//SELECT * FROM table WHERE … LIMIT 10; #返回前10行 
		//SELECT * FROM table WHERE … LIMIT 0,10; #返回前10行
		//SELECT * FROM table WHERE … LIMIT 10,20; #返回第10-20行数据
		
		int start = (pageNumber - 1) * pageSize;
		
		List<Article> result = getJdbcTemplate().query("SELECT * FROM article ORDER BY postDate DESC LIMIT ?,? ", 
				new Object[] { start, pageSize}, 
				new int[] { Types.INTEGER, Types.INTEGER}, 
				new ArticleRowMapper());
		
		for (Article article : result) {
			fillArticleBeanInfo(article);
		}
		
		//Collections.sort(result);
		return result;
	}

	@Override
	public User getAuthorByArticleID(String articleID) {
		return (User)getJdbcTemplate().queryForObject(
				"SELECT u.userID, u.email, u.nickName, u.password FROM User u, Article a WHERE u.userID=a.userID and a.articleID = ?", 
				new Object[] { articleID }, 
				new int[] { Types.VARCHAR},
				new UserRowMapper());
	}

	public void fillArticleBeanInfo(Article a)
	{
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		IArticleDAO articleDAO = daoFactory.getArticleDAO();
		IArticleCategoryDAO articleCategoryDAO = daoFactory.getArticleCategoryDAO();
		IArticleTagDAO articleTagDAO = daoFactory.getArticleTagDAO();
		ICommentsDAO commentsDAO = daoFactory.getCommentsDAO();
		
		String articleID = a.getArticleID();
		
		User author = articleDAO.getAuthorByArticleID(articleID);
		
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		tagList.addAll(articleTagDAO.getTagListByArticleID(articleID));
		
		ArrayList<Comments> commentsList = new ArrayList<Comments>();
		commentsList.addAll(commentsDAO.queryByArticleID(articleID));
		
		ArrayList<Category> categoryList = new ArrayList<Category>();
		categoryList.addAll(articleCategoryDAO.getCategoryListByArticleID(articleID));
		
		a.setAuthor(author);
		a.setTagList(tagList);
		a.setCommentsList(commentsList);
		a.setCategoryList(categoryList);
	}
		
}
