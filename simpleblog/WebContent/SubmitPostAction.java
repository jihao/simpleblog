package cn.heapstack.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IArticleDAO;
import cn.heapstack.simpleblog.dao.IArticleTagDAO;
import cn.heapstack.simpleblog.dao.ICategoryDAO;
import cn.heapstack.simpleblog.dao.ITagDAO;
import cn.heapstack.simpleblog.domain.Article;
import cn.heapstack.simpleblog.domain.Category;
import cn.heapstack.simpleblog.domain.Tag;
import cn.heapstack.simpleblog.domain.User;

/**
 * Servlet to handle the submit post request
 * 
 * @author ehaojii
 *
 */
public class SubmitPostAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7397284824484835880L;

	Logger logger = LoggerFactory.getLogger(SubmitPostAction.class);
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String isSaveAsDraft = req.getParameter("postAsDraft");
		boolean asDraft = (isSaveAsDraft.compareToIgnoreCase("TRUE")==0);
		String title = req.getParameter("title");
		String categoryName = req.getParameter("category");
		String tags = req.getParameter("tags");
		String content = req.getParameter("EditorDefault");
		
		if(categoryName.isEmpty()) {
			categoryName = "UnCategorized";
		}
		Category c  = new Category(categoryName);
		ICategoryDAO categoryDAO = DAOFactory.getDAOFactory().getCategoryDAO("CategoryDAODemo");
		if(!categoryDAO.isCategoryExist(categoryName)) {
			categoryDAO.addCategory(c);
		}
		
		ITagDAO tagDAO = DAOFactory.getDAOFactory().getTagDAO("TagDAODemo");
		
		// tags support space | , ; as separator
		ArrayList<Tag> tagArrayList = new ArrayList<Tag>();
		if (!tags.isEmpty()) {
			String [] tagArray = tags.split("[ |\\||,|;]+");
			for (String tagName : tagArray) {
				Tag tag = new Tag(tagName);
				tagArrayList.add(tag);
				if(!tagDAO.isTagExist(tagName)) {
					tagDAO.addTag(tag);
				}
			}
		}
		logger.info("TagArrayList size:{}",tagArrayList.size());
		
		User user = (User)req.getSession().getAttribute("user");
		
		Article article = new Article();
		article.setCategory(c);
		article.setTitle(title);
		article.setAsDraft(asDraft);
		article.setContent(content);
		article.setTagList(tagArrayList);
		article.setAuthor(user);
		
		IArticleDAO entryDAO = DAOFactory.getDAOFactory().getArticleDAO("EntryDAODemo");
		int entryID = entryDAO.addArticle(article);
		
		IArticleTagDAO entryTagDAO = DAOFactory.getDAOFactory().getArticleTagDAO("EntryTagDAODemo");
		for (Tag tag : tagArrayList) {
			entryTagDAO.addRecord(entryID, tag.getTagID());
		}
		//entryTagDAO.addRecord(entryID, tagID);
		Enumeration<String> params  = (Enumeration<String>)req.getParameterNames();
		while(params.hasMoreElements())
		{
			String param = params.nextElement();
			
			logger.info("{}:{}",param,req.getParameterValues(param)[0]);
			
		}
		//StringEscapeUtils.escapeHtml(str)
		//Entry Bean will store the unEscaped HTML, DAO will first escapeHTML then save to DB

		req.setAttribute("article", article);
		
		req.getRequestDispatcher("showpost.jsp").forward(req, resp);
	}
}
