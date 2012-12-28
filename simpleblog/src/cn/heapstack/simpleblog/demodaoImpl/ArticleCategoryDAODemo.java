package cn.heapstack.simpleblog.demodaoImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IArticleCategoryDAO;
import cn.heapstack.simpleblog.dao.IArticleDAO;
import cn.heapstack.simpleblog.domain.Article;
import cn.heapstack.simpleblog.domain.Category;

public class ArticleCategoryDAODemo implements IArticleCategoryDAO {

	public static ArrayList<ArticleCategoryRecord> record = new ArrayList<ArticleCategoryRecord>(); 
	Logger logger = LoggerFactory.getLogger(ArticleCategoryDAODemo.class);
	
	public boolean addRecord(String articleID, String categoryID) {
		return record.add(new ArticleCategoryRecord(Integer.valueOf(articleID),Integer.valueOf(categoryID)));
	}
	
	public boolean removeRecordByArticleID(String articleID) {
		boolean result = false;
		ArticleCategoryRecord toBeRemoved = null;
		for (ArticleCategoryRecord articleCategoryRecord : record) {
			if (articleCategoryRecord.getArticleID()==Integer.valueOf(articleID))
			{
				toBeRemoved = articleCategoryRecord;
				result = true;
				break;
			}
		} 
		record.remove(toBeRemoved);
		return result;
	}

	
	public boolean removeRecordByCategoryName(String categoryID) {
		boolean result = false;
		ArticleCategoryRecord toBeRemoved = null;
		for (ArticleCategoryRecord articleCategoryRecord : record) {
			if (articleCategoryRecord.getCategoryID()==Integer.valueOf(categoryID))
			{
				toBeRemoved = articleCategoryRecord;
				result = true;
				break;
			}
		} 
		record.remove(toBeRemoved);
		return result;
	}
	
	public List<Article> searchArticlesByCategoryID(String categoryID) {
		List<Article> searchResult = new ArrayList<Article>();
		for (ArticleCategoryRecord articleCategoryRecord : record) {
			if (articleCategoryRecord.getCategoryID()==Integer.valueOf(categoryID))
			{
				int articleID = articleCategoryRecord.getArticleID();
				IArticleDAO articleDAO = DAOFactory.getDAOFactory().getArticleDAO();
				Article a = articleDAO.getArticle(String.valueOf(articleID));
				searchResult.add(a);
			}
		} 
		
		Collections.sort(searchResult);
		return searchResult;
	}
	
	class ArticleCategoryRecord
	{
		private int articleID;
		private int categoryID;
		public ArticleCategoryRecord(int articleID, int categoryID){
			this.articleID = articleID;
			this.categoryID = categoryID;
		}

		public int getArticleID() {
			return articleID;
		}

		public void setArticleID(int articleID) {
			this.articleID = articleID;
		}

		public int getCategoryID() {
			return categoryID;
		}
		public void setCategoryID(int categoryID) {
			this.categoryID = categoryID;
		}
	}

	
	public List<Category> getCategoryListByArticleID(String articleID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getArticleCountsRelatedWithCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Article> searchArticlesByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}




}
