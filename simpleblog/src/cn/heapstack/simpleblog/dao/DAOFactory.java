package cn.heapstack.simpleblog.dao;

import javax.sql.DataSource;


import cn.heapstack.simpleblog.demodaoImpl.ArticleCategoryDAODemo;
import cn.heapstack.simpleblog.demodaoImpl.ArticleDAODemo;
import cn.heapstack.simpleblog.demodaoImpl.ArticleTagDAODemo;
import cn.heapstack.simpleblog.demodaoImpl.CategoryDAODemo;
import cn.heapstack.simpleblog.demodaoImpl.CommentsDAODemo;
import cn.heapstack.simpleblog.demodaoImpl.TagDAODemo;
import cn.heapstack.simpleblog.demodaoImpl.UserDAODemo;
import cn.heapstack.simpleblog.jdbctemplatedaoImpl.ArticleCategoryDaoJdbcTemplate;
import cn.heapstack.simpleblog.jdbctemplatedaoImpl.ArticleDaoJdbcTemplate;
import cn.heapstack.simpleblog.jdbctemplatedaoImpl.ArticleTagDaoJdbcTemplate;
import cn.heapstack.simpleblog.jdbctemplatedaoImpl.CategoryDaoJdbcTemplate;
import cn.heapstack.simpleblog.jdbctemplatedaoImpl.CommentsDaoJdbcTemplate;
import cn.heapstack.simpleblog.jdbctemplatedaoImpl.TagDaoJdbcTemplate;
import cn.heapstack.simpleblog.jdbctemplatedaoImpl.UserDaoJdbcTemplate;

public class DAOFactory {

	public static boolean runDemo = false;
	private static DAOFactory instance = null;
	private DataSource dataSource = null;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private DAOFactory() {
	}

	public synchronized static DAOFactory getDAOFactory() {
		if (instance == null) {
			instance = new DAOFactory();
		}

		return instance;
	}

	public IUserDAO getUserDAO() {
		if (DAOFactory.runDemo) {
			return new UserDAODemo();
		}

		UserDaoJdbcTemplate dao = new UserDaoJdbcTemplate();
		dao.setDataSource(dataSource);
		return dao;
	}

	public ICategoryDAO getCategoryDAO() {
		if (DAOFactory.runDemo) {
			return new CategoryDAODemo();
		}

		CategoryDaoJdbcTemplate dao = new CategoryDaoJdbcTemplate();
		dao.setDataSource(dataSource);
		return dao;
	}

	public IArticleDAO getArticleDAO() {
		if (DAOFactory.runDemo) {
			return new ArticleDAODemo();
		}

		ArticleDaoJdbcTemplate dao = new ArticleDaoJdbcTemplate();
		dao.setDataSource(dataSource);
		return dao;
	}

	public ITagDAO getTagDAO() {
		if (DAOFactory.runDemo) {
			return new TagDAODemo();
		}
		
		TagDaoJdbcTemplate dao = new TagDaoJdbcTemplate();
		dao.setDataSource(dataSource);
		return dao;
	}

	public IArticleTagDAO getArticleTagDAO() {
		if (DAOFactory.runDemo) {
			return new ArticleTagDAODemo();
		}

		ArticleTagDaoJdbcTemplate dao = new ArticleTagDaoJdbcTemplate();
		dao.setDataSource(dataSource);
		return dao;
	}

	public IArticleCategoryDAO getArticleCategoryDAO() {
		if (DAOFactory.runDemo) {
			return new ArticleCategoryDAODemo();
		}

		ArticleCategoryDaoJdbcTemplate dao = new ArticleCategoryDaoJdbcTemplate();
		dao.setDataSource(dataSource);
		return dao;
	}

	public ICommentsDAO getCommentsDAO() {
		if (DAOFactory.runDemo) {
			return new CommentsDAODemo();
		}

		CommentsDaoJdbcTemplate dao = new CommentsDaoJdbcTemplate();
		dao.setDataSource(dataSource);
		return dao;
	}
}
