package cn.heapstack.simpleblog.demodaoImpl;

import java.util.HashMap;
import java.util.List;

import cn.heapstack.simpleblog.dao.ICommentsDAO;
import cn.heapstack.simpleblog.domain.Comments;

public class CommentsDAODemo implements ICommentsDAO {

	public static HashMap<String, Comments> commentsMap = new HashMap<String, Comments>();
	static int commentsIDSeed = 0;
	
	@Override
	public boolean addComments(Comments comments) {
		int nextID = ++commentsIDSeed;
		commentsMap.put(String.valueOf(nextID), comments);
		comments.setCommentsID(String.valueOf(nextID));
		return true;
	}

	@Override
	public boolean deleteComments(String commentsID) {
		commentsMap.remove(String.valueOf(commentsID));
		return true;
	}

	@Override
	public Comments getComments(String commentsID) {
		return commentsMap.get(String.valueOf(commentsID));
	}

	@Override
	public List<Comments> queryAll() {
		return null;
	}

	@Override
	public List<Comments> queryByArticleID(String articleID) {
		return null;
	}

	@Override
	public boolean updateComments(Comments comments) {
		commentsMap.put(String.valueOf(comments.getCommentsID()), comments);
		return true;
	}

}
