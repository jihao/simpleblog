package cn.heapstack.simpleblog.dao;

import java.util.List;

import cn.heapstack.simpleblog.domain.Article;
import cn.heapstack.simpleblog.domain.User;

public interface IArticleDAO {

	boolean isArticleExist(String articleID);
	
	boolean addArticle(Article e);
	
	Article getArticle(String articleID);
	
	boolean updateArticle(Article e);
	
	boolean deleteArticle(String articleID);
	
	User getAuthorByArticleID(String articleID);
	
	int getArticleTotalCount();
	
	List<Article> queryAll();
	
	List<Article> queryByName(String title);
	
	List<Article> queryByPage(int pageNumber, int pageSize);
	
	/**
	 * This method fill the article attributes from database
	 * 
	 * @param a
	 * 	a only contains basic article attributes, 
	 * 	a does not have list attributes value like tagList, categoryList
	 */
	void fillArticleBeanInfo(Article a);
	
}
