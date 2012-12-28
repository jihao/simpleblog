package cn.heapstack.servlet.dwr;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.beanutils.BeanPrinter;
import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IArticleDAO;
import cn.heapstack.simpleblog.dao.ICommentsDAO;
import cn.heapstack.simpleblog.domain.Comments;

public class CommentsAction {
	private DAOFactory daoFactory = DAOFactory.getDAOFactory();
	private ICommentsDAO commentDAO = daoFactory.getCommentsDAO();
	private IArticleDAO articleDAO = daoFactory.getArticleDAO();
	Logger logger = LoggerFactory.getLogger(CommentsAction.class);
	public CommentsAction()
	{		
	}
	
	/**
	 * The DWR service for the AJAX way of comments
	 *  
	 * @param comments
	 * @return
	 */
	public Comments addComments(Comments comments)
	{
		
		logger.info("Comments for artilce {}",comments.getTheCommentsArticleID());
		
		String commentsID = UUID.randomUUID().toString();
		comments.setCommentsID(commentsID);
		
		BeanPrinter.printBean(comments);
		commentDAO.addComments(comments);
		
		articleDAO.getArticle(comments.getTheCommentsArticleID()).getCommentsList().add(comments);
		return comments;
	}
}
