package cn.heapstack.simpleblog.dao;

import java.util.List;

import cn.heapstack.simpleblog.domain.Article;
import cn.heapstack.simpleblog.domain.Tag;

public interface IArticleTagDAO {

	boolean addRecord(String articleID,String tagName);
	
	boolean removeRecord(String articleID,String tagName);
	
	boolean removeRecordByArticleID(String articleID);
	
	boolean removeRecordByTagName(String tagName);
	
	int getArticleCountsRelatedWithTagName(String tagName);
	
	/**
	 * This interface implementation need to search everything of the Article related with the tagName
	 * 
	 * @param tagName
	 * @return
	 */
	List<Article> searchArticlesByTagName(String tagName);
	
	List<Tag> getTagListByArticleID(String articleID);
	
	/**
	 * Get the tag IDs related with this article
	 * @param articleID
	 * @return
	 * 		list contains the tag IDs related with the article
	 */
	List<String> getTagNamesRelatedWithArticleID(String articleID);
}
