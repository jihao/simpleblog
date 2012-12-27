package cn.heapstack.simpleblog.demodaoImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IArticleDAO;
import cn.heapstack.simpleblog.dao.IArticleTagDAO;
import cn.heapstack.simpleblog.domain.Article;
import cn.heapstack.simpleblog.domain.Tag;

public class ArticleTagDAODemo implements IArticleTagDAO {

	public static ArrayList<ArticleTagRecord> record = new ArrayList<ArticleTagRecord>(); 
	@Override
	public boolean addRecord(String articleID, String tagID) {
		return record.add(new ArticleTagRecord(articleID,tagID));
	}
	
	@Override
	public boolean removeRecord(String articleID, String tagID) {
		boolean result = false;
		ArticleTagRecord toBeRemoved = null;
		for (ArticleTagRecord articleTagRecord : record) {
			if (articleTagRecord.getArticleID()==articleID
					&& articleTagRecord.getTagID()==tagID)
			{
				toBeRemoved = articleTagRecord;
				result = true;
				break;
			}
		} 
		record.remove(toBeRemoved);
		return result;
	}

	@Override
	public boolean removeRecordByArticleID(String articleID) {
		boolean result = false;
		ArticleTagRecord toBeRemoved = null;
		for (ArticleTagRecord articleTagRecord : record) {
			if (articleTagRecord.getArticleID()==articleID)
			{
				toBeRemoved = articleTagRecord;
				result = true;
				break;
			}
		} 
		record.remove(toBeRemoved);
		return result;
	}

	@Override
	public boolean removeRecordByTagName(String tagID) {
		boolean result = false;
		ArticleTagRecord toBeRemoved = null;
		for (ArticleTagRecord articleTagRecord : record) {
			if (articleTagRecord.getTagID()==tagID)
			{
				toBeRemoved = articleTagRecord;
				result = true;
				break;
			}
		} 
		record.remove(toBeRemoved);
		return result;
	}	
	class ArticleTagRecord
	{
		private int articleID;
		private int tagID;
		public ArticleTagRecord(int articleID, int tagID){
			this.articleID = articleID;
			this.tagID = tagID;
		}

		public int getArticleID() {
			return articleID;
		}

		public void setArticleID(int articleID) {
			this.articleID = articleID;
		}

		public int getTagID() {
			return tagID;
		}
		public void setTagID(int tagID) {
			this.tagID = tagID;
		}
	}
	
	@Override
	public List<Article> searchArticlesByTagName(String tagID) {
		List<Article> searchResult = new ArrayList<Article>();
		for (ArticleTagRecord articleTagRecord : record) {
			if (articleTagRecord.getTagID() == tagID)
			{
				int articleID = articleTagRecord.getArticleID();
				IArticleDAO articleDAO = DAOFactory.getDAOFactory().getArticleDAO();
				Article a = articleDAO.getArticle(articleID);
				searchResult.add(a);
			}
		} 
		
		Collections.sort(searchResult);
		return searchResult;
	}
	@Override
	public List<Integer> getTagNamesRelatedWithArticleID(String articleID) {
		ArrayList<Integer> tagIDs = new ArrayList<Integer>();
		for (ArticleTagRecord articleTagRecord : record) {
			if (articleTagRecord.getArticleID()==articleID)
			{
				tagIDs.add(articleTagRecord.tagID);
			}
		}
		return tagIDs;
	}

	@Override
	public List<Tag> getTagListByArticleID(String articleID) {
		// TODO Auto-generated method stub
		return null;
	}

}
