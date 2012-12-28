package cn.heapstack.simpleblog.demodaoImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.simpleblog.dao.IArticleDAO;
import cn.heapstack.simpleblog.domain.Article;
import cn.heapstack.simpleblog.domain.User;

public class ArticleDAODemo implements IArticleDAO {

	public static HashMap<String, Article> articleMap = new HashMap<String, Article>();
	private static int id = 0;
	Logger logger = LoggerFactory.getLogger(ArticleDAODemo.class);
	
	
	public boolean addArticle(Article article) {
		article.setArticleID(String.valueOf(++id));
		ArticleDAODemo.articleMap.put(String.valueOf(article.getArticleID()), article);
		return true;
	}

	
	public boolean checkExist(int articleID) {
		return (ArticleDAODemo.articleMap.get(String.valueOf(articleID))!=null);
	}

	
	public boolean deleteArticle(int articleID) {
		return (ArticleDAODemo.articleMap.remove(String.valueOf(articleID))!=null);
	}

	
	public Article getArticle(int articleID) {
		for (String entryID : ArticleDAODemo.articleMap.keySet()) {
			if(entryID.equals(String.valueOf(articleID)))
				return articleMap.get(entryID);
		}
		
		return null;
	}

	
	public List<Article> queryAll() {
		List<Article> entryList = new ArrayList<Article>();
		for (String entryID : ArticleDAODemo.articleMap.keySet()) {
			entryList.add( ArticleDAODemo.articleMap.get(entryID) );
		}
		
		Collections.sort(entryList);
		return entryList;
	}

	
	public List<Article> queryByName(String title) {
		
		List<Article> entryList = new ArrayList<Article>();
		for (String entryID : ArticleDAODemo.articleMap.keySet()) {
			Article entry = ArticleDAODemo.articleMap.get(entryID);
			if(entry.getTitle().contains(title)) {
				entryList.add(entry);
			}
		}
		Collections.sort(entryList);
		return entryList;
	}

	
	public boolean updateArticle(Article article) {
		ArticleDAODemo.articleMap.put(String.valueOf(article.getArticleID()), article);;
		return true;
	}

	
	public int getArticleTotalCount() {
		return ArticleDAODemo.id;
	}

	
	public List<Article> queryByPage(int pageNumber, int pageSize) {
		
		List<Article> ret = new ArrayList<Article>();
		int start = (pageNumber - 1) * pageSize;
		int end = start + pageSize - 1;
		
		List<Article> cacheAllArticles = queryAll();
		
		if (start >= cacheAllArticles.size())
			return ret;
		
		for (int i = start; i <= end; i++) {
			if (i < cacheAllArticles.size())
				ret.add(cacheAllArticles.get(i));
		}
		
		Collections.sort(ret);
		return ret;
	}

	
	public void fillArticleBeanInfo(Article a) {
		// TODO Auto-generated method stub
		
	}

	
	public User getAuthorByArticleID(int articleID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isArticleExist(String articleID) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Article getArticle(String articleID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean deleteArticle(String articleID) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public User getAuthorByArticleID(String articleID) {
		// TODO Auto-generated method stub
		return null;
	}

}
