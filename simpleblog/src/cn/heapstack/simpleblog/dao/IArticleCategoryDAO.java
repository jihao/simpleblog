package cn.heapstack.simpleblog.dao;

import java.util.List;

import cn.heapstack.simpleblog.domain.Article;
import cn.heapstack.simpleblog.domain.Category;

public interface IArticleCategoryDAO {

	boolean addRecord(String articleID,String categoryName);
	
	boolean removeRecordByArticleID(String articleID);
	
	boolean removeRecordByCategoryName(String categoryName);
	
	int getArticleCountsRelatedWithCategoryName(String categoryName);
	
	List<Article> searchArticlesByCategoryName(String categoryName);
	
	List<Category> getCategoryListByArticleID(String articleID);
}
