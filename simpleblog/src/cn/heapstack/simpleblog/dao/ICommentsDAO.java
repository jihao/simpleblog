package cn.heapstack.simpleblog.dao;

import java.util.List;

import cn.heapstack.simpleblog.domain.Comments;

public interface ICommentsDAO {

	boolean addComments(Comments comments);

	Comments getComments(String commentsID);

	boolean updateComments(Comments comments);
	
	boolean deleteComments(String commentsID);



	List<Comments> queryAll();
	List<Comments> queryByArticleID(String articleID);
}
