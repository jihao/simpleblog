package cn.heapstack.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import cn.heapstack.beanutils.BeanPrinter;
import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IArticleCategoryDAO;
import cn.heapstack.simpleblog.dao.IArticleDAO;
import cn.heapstack.simpleblog.dao.IArticleTagDAO;
import cn.heapstack.simpleblog.dao.ICategoryDAO;
import cn.heapstack.simpleblog.dao.ITagDAO;
import cn.heapstack.simpleblog.domain.Article;
import cn.heapstack.simpleblog.domain.Category;
import cn.heapstack.simpleblog.domain.Tag;
import cn.heapstack.simpleblog.domain.User;
import cn.heapstack.simpleblog.utils.bean.Message;
import cn.heapstack.simpleblog.utils.bean.MessageStyle;
import cn.heapstack.utils.MyCollectionUtils;

/**
 * Servlet implementation class PostsServlet
 */
public class PostsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory daoFactory = DAOFactory.getDAOFactory();
	

	Logger logger = LoggerFactory.getLogger(PostsAction.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostsAction() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		logger.info("Get Method:{}", method);

		dispatchDoGetRequest(method, request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		logger.info("Post Method:{}", method);

		dispatchDoPostRequest(method, request, response);
	}

	private void dispatchDoGetRequest(String method,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (method == null || method.isEmpty()) {
			processHomePageRequest(request, response);
		} else if (method.equals("homelist")) {
			processHomePageRequest(request, response);
		} else if (method.equals("view")) {
			processViewPostRequest(request, response);
		} else if (method.equals("delete")) {
			processDeletePostRequest(request, response);
		} else if (method.equals("edit")) {
			processEditPostRequest(request, response);
		} else if (method.equals("list")) {
			processListPostRequest(request, response);
		} else if (method.equals("new")) {
			processNewPostRequest(request, response);
		} else if (method.equals("search")) {
			processSearchPostRequest(request, response);
		}
	}
	
	private void dispatchDoPostRequest(String method,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (method == null || method.isEmpty()) {
			processListPostRequest(request, response);
		} else if (method.equals("save")) {
			processSavePostRequest(request, response);
		} else if (method.equals("update")) {
			processUpdatePostRequest(request, response);
		} else if (method.equals("delete")) {
			processPostDeletePostRequest(request, response);
		}

	}

	@SuppressWarnings("unchecked")
	private void processUpdatePostRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		IArticleDAO articleDAO = daoFactory.getArticleDAO();
		ICategoryDAO categoryDAO = daoFactory.getCategoryDAO();
		ITagDAO tagDAO = daoFactory.getTagDAO();
		IArticleTagDAO articleTagDAO = daoFactory.getArticleTagDAO();
		IArticleCategoryDAO articleCategoryDAO = daoFactory
				.getArticleCategoryDAO();
		
		String articleIDStr = request.getParameter("updateArticleID");

		String isSaveAsDraft = request.getParameter("postAsDraft");
		boolean asDraft = (isSaveAsDraft.compareToIgnoreCase("TRUE") == 0);
		String title = request.getParameter("title");
		String[] categoryNames = request.getParameterValues("categoryNames");
		String tags = request.getParameter("tags");
		String content = request.getParameter("EditorDefault");

		Message m = new Message();
		try
		{
			ArrayList<Category> categoryList = new ArrayList<Category>();
			if (categoryNames != null && categoryNames.length != 0)
			{
				for (String categoryName : categoryNames) 
				{
					Category c = categoryDAO.getCategory(categoryName);
					categoryList.add(c);
				}
			} 
			else 
			{
				String categoryName = "UnCategorized";
				if (!categoryDAO.isCategoryExist(categoryName)) 
				{
					Category c = new Category(categoryName);
					categoryDAO.addCategory(c);
					categoryList.add(c);
				} 
				else 
				{
					categoryList.add(categoryDAO.getCategory(categoryName));
				}
			}
	
			ArrayList<String> oldTagArrayList = new ArrayList<String>();
			List<String> oldTagNamesArrayList = articleTagDAO.getTagNamesRelatedWithArticleID(articleIDStr);
			oldTagArrayList.addAll(oldTagNamesArrayList);
			
			
			ArrayList<String> newTagArrayList = new ArrayList<String>();
			if (!tags.isEmpty()) {
				// tags support space | , ; as separator
				Collection<String> unDuplicatedTags = MyCollectionUtils.removeDuplicates(Arrays.asList(tags.split("[ |\\||,|;]+")));
				newTagArrayList.addAll(unDuplicatedTags);
			}
			
			
			ArrayList<String> toBeRemovedTagArrayList = (ArrayList<String>)MyCollectionUtils.removeAll(oldTagArrayList, newTagArrayList);
			ArrayList<String> toBeAddedTagArrayList = (ArrayList<String>)MyCollectionUtils.removeAll(newTagArrayList, oldTagArrayList);
			ArrayList<String> alreadyExistedTagArrayList = (ArrayList<String>)CollectionUtils.retainAll(newTagArrayList, oldTagArrayList);
			
			logger.info("toBeRemovedTagArrayList:{};",toBeRemovedTagArrayList.size());
			logger.info("toBeAddedTagArrayList:{};",toBeAddedTagArrayList.size());
			logger.info("alreadyExistedTagArrayList:{};",alreadyExistedTagArrayList.size());
			
			ArrayList<Tag> tagArrayList = new ArrayList<Tag>();
			for (String alreadyExistTagName : alreadyExistedTagArrayList) {
				Tag tag = tagDAO.getTag(alreadyExistTagName);
				tagArrayList.add(tag);
			}
			
			for (String toBeRemovedTagName : toBeRemovedTagArrayList) {
				articleTagDAO.removeRecord(articleIDStr, toBeRemovedTagName);
			}
			
			for (String toBeCreatedTagName : toBeAddedTagArrayList) {
				Tag tag = new Tag(toBeCreatedTagName);
				if (!tagDAO.isTagExist(toBeCreatedTagName)) 
				{
					tagDAO.addTag(tag);
				}
				else
				{
					tag = tagDAO.getTag(toBeCreatedTagName);
				}
				tagArrayList.add(tag);
				articleTagDAO.addRecord(articleIDStr, tag.getTagName());
			}
	
			User user = (User) request.getSession().getAttribute("user");
	
			Article article = new Article();
			article.setArticleID(articleIDStr);
			article.setCategoryList(categoryList);
			article.setTitle(title);
			article.setAsDraft(asDraft);
			article.setContent(content);
			article.setTagList(tagArrayList);
			article.setAuthor(user);
	
			BeanPrinter.printBean(article);
			// boolean succeed =
			articleDAO.updateArticle(article);
	
			articleCategoryDAO.removeRecordByArticleID(articleIDStr);
			for (Category category : categoryList) {
				articleCategoryDAO.addRecord(articleIDStr, category.getCategoryName());
			}
	
			// StringEscapeUtils.escapeHtml(str)
			// Entry Bean will store the unEscaped HTML, DAO will first escapeHTML
			// then save to DB
			request.setAttribute("article", article);
			m.setStyle(MessageStyle.info);
			m.setContent("Update article succeed");
		}
		catch(DataAccessException ex)
		{
			m.setStyle(MessageStyle.errors);
			m.setContent(ex.getLocalizedMessage());
		}
		request.setAttribute("message", m);
		logger.info("style: {},content: {}",new Object[]{m.getStyle(),m.getContent()});
		request.getRequestDispatcher("showpost.jsp").forward(request, response);
	}

	private void processSavePostRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IArticleDAO articleDAO = daoFactory.getArticleDAO();
		ICategoryDAO categoryDAO = daoFactory.getCategoryDAO();
		ITagDAO tagDAO = daoFactory.getTagDAO();
		IArticleTagDAO articleTagDAO = daoFactory.getArticleTagDAO();
		IArticleCategoryDAO articleCategoryDAO = daoFactory
				.getArticleCategoryDAO();
		
		String isSaveAsDraft = request.getParameter("postAsDraft");
		boolean asDraft = (isSaveAsDraft.compareToIgnoreCase("TRUE") == 0);
		String title = request.getParameter("title");
		String[] categoryNames = request.getParameterValues("categoryNames");
		String tags = request.getParameter("tags");
		String content = request.getParameter("EditorDefault");

		Message m = new Message();
		try
		{
			logger.info("title : {} , tags : {}", new Object[] {title, tags});
			ArrayList<Category> categoryList = new ArrayList<Category>();
			if (categoryNames != null && categoryNames.length != 0) 
			{
				for (String categoryName : categoryNames) 
				{
					Category c = categoryDAO.getCategory(categoryName);
					categoryList.add(c);
				}
			}
			else 
			{
				String categoryName = "UnCategorized";
				if (!categoryDAO.isCategoryExist(categoryName)) 
				{
					Category c = new Category(categoryName);
					categoryDAO.addCategory(c);
					categoryList.add(c);
				} 
				else 
				{
					categoryList.add(categoryDAO.getCategory(categoryName));
				}
			}
	
			// tags support space | , ; as separator
			ArrayList<Tag> tagArrayList = new ArrayList<Tag>();
			ArrayList<String> newTagArrayList = new ArrayList<String>();
			if (!tags.isEmpty()) {
				Collection<String> unDuplicatedTags = MyCollectionUtils.removeDuplicates(Arrays.asList(tags.split("[ |\\||,|;]+")));
				newTagArrayList.addAll(unDuplicatedTags);
			}
			for (String tagName : newTagArrayList) {
				Tag tag = new Tag(tagName);
				if (!tagDAO.isTagExist(tagName)) {
					tagDAO.addTag(tag);
				}
				else
				{
					tag = tagDAO.getTag(tagName);
				}
				tagArrayList.add(tag);
			}
			
			logger.info("TagArrayList size:{}", tagArrayList.size());
			
			User user = (User) request.getSession().getAttribute("user");
			
			String articleID = UUID.randomUUID().toString();
			
			Article article = new Article();
			article.setArticleID(articleID);
			article.setCategoryList(categoryList);
			article.setTitle(title);
			article.setAsDraft(asDraft);
			article.setContent(content);
			article.setTagList(tagArrayList);
			article.setAuthor(user);
			article.setSetTop(false);
			article.setPostDate(new Date());
			//TODO: check here
			article.setStaticLinkURL("");
	
			logger.info("Category Size: {}", article.getCategoryList().size());
			for (Category c : article.getCategoryList()) {
				logger.info(c.getCategoryName());
			}
			
			articleDAO.addArticle(article);
			
			for (Tag tag : tagArrayList) {
				articleTagDAO.addRecord(article.getArticleID(), tag.getTagName());
			}
			for (Category category : categoryList) {
				articleCategoryDAO.addRecord(article.getArticleID(), category.getCategoryName());
			}
			// articleTagDAO.addRecord(articleID, tagID);
			/*
			 * Enumeration<String> params =
			 * (Enumeration<String>)req.getParameterNames();
			 * while(params.hasMoreElements()) { String param =
			 * params.nextElement();
			 * 
			 * logger.info("{}:{}",param,req.getParameterValues(param)[0]);
			 * 
			 * }
			 */
			// StringEscapeUtils.escapeHtml(str)
			// Entry Bean will store the unEscaped HTML, DAO will first escapeHTML
			// then save to DB
			request.setAttribute("article", article);
			m.setStyle(MessageStyle.info);
			m.setContent("Add article succeed");
		}
		catch(DataAccessException ex)
		{
			m.setStyle(MessageStyle.errors);
			m.setContent(ex.getLocalizedMessage());
		}
		request.setAttribute("message", m);
		request.getRequestDispatcher("showpost.jsp").forward(request, response);

	}

	private void processPostDeletePostRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IArticleDAO articleDAO = daoFactory.getArticleDAO();
		
		String confirmDelete = request.getParameter("confirmDelete");
		if (confirmDelete != null && !confirmDelete.isEmpty()) {
			String articleID = request.getParameter("articleID");
			logger.info("Confirmed delete the post, articleID:{}", articleID);
			Message m = new Message();
			try
			{
				articleDAO.deleteArticle(articleID);
				m.setStyle(MessageStyle.info);
				m.setContent("Delete article succeed");
			}
			catch(DataAccessException e)
			{
				m.setStyle(MessageStyle.errors);
				m.setContent(e.getLocalizedMessage());
			}
			request.setAttribute("message", m);
			processListPostRequest(request, response);
		}
	}



	/**
	 * 处理根据Category或者Tag查询的请求，结果仅显示文章标题
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void processSearchPostRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IArticleTagDAO articleTagDAO = daoFactory.getArticleTagDAO();
		IArticleCategoryDAO articleCategoryDAO = daoFactory
				.getArticleCategoryDAO();
		
		String categoryNameStr = request.getParameter("categoryName");
		String tagNameStr = request.getParameter("tagName");
				
		List<Article> articleList = new ArrayList<Article>();
		if (categoryNameStr != null && !categoryNameStr.isEmpty()) {
			List<Article> articleResultList = articleCategoryDAO.searchArticlesByCategoryName(categoryNameStr);
			articleList.addAll(articleResultList);
			logger.info("Search by category ID : {}, result articleList size : {}",new String[] { categoryNameStr, String.valueOf(articleList.size())});
		}
		
		if (tagNameStr != null && !tagNameStr.isEmpty()) {
			List<Article> articleResultList = articleTagDAO.searchArticlesByTagName(tagNameStr);
			articleList.addAll(articleResultList);
			logger.info("Search by tagName : {}, result articleList size : {}",new String[] { tagNameStr, String.valueOf(articleList.size())});
		}
		
		request.setAttribute("articleList", articleList);
		
		request.getRequestDispatcher("searchresult.jsp").forward(request, response);
	}

	private void processHomePageRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IArticleDAO articleDAO = daoFactory.getArticleDAO();
		
		String pageNumberStr = request.getParameter("pageNumber");
		int pageNumber = 1;
		if (pageNumberStr != null && !pageNumberStr.isEmpty()) {
			pageNumber = Integer.parseInt(pageNumberStr);
		}

		String pageSizeString = this.getServletContext().getInitParameter(
				"pageSize");
		int pageSize = Integer.valueOf(pageSizeString); // 分页大小
		int totalPosts = articleDAO.getArticleTotalCount(); // 总文章数
		int totalPages = totalPosts / pageSize + (totalPosts == 0 ? 1 : 0)
				+ ((totalPosts % pageSize) > 0 ? 1 : 0); // 计算得出的总页数

		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalPosts", totalPosts);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("totalPages", totalPages);

		List<Article> articleList = articleDAO
				.queryByPage(pageNumber, pageSize);

		logger.info("ListArticle get articleList size:{}", articleList.size());
		request.setAttribute("articleList", articleList);

		request.getRequestDispatcher("home.jsp").forward(request, response);

	}

	private void processNewPostRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		setCategoryListRequestAttribute(request);
		request.getRequestDispatcher("newpost.jsp").forward(request, response);
	}

	/**
	 * 查询所有的分类，并存储到request的categoryList属性
	 * 
	 * @param request
	 */
	private void setCategoryListRequestAttribute(HttpServletRequest request) {
		ICategoryDAO categoryDAO = daoFactory.getCategoryDAO();

		List<Category> categoryList = categoryDAO.queryAll();
		logger.info("request.setAttribute(categoryList), and its size is: {}",
				categoryList.size());
		request.setAttribute("categoryList", categoryList);
	}

	private void processDeletePostRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String articleID = request.getParameter("articleID");
		request.setAttribute("articleID", articleID);
		
		// Show the preview of this article, might be better
		IArticleDAO articleDAO = daoFactory.getArticleDAO();
		Article article = articleDAO.getArticle(articleID);
		request.setAttribute("article", article);
		
		request.getRequestDispatcher("deletepost.jsp").forward(request,
				response);
	}

	private void processEditPostRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IArticleDAO articleDAO = daoFactory.getArticleDAO();
		
		String articleID = request.getParameter("articleID");
		Article article = articleDAO.getArticle(articleID);
		request.setAttribute("article", article);
		logger.info("User want to edit article with title {}", article
				.getTitle());

		request.setAttribute("method", "update");

		setCategoryListRequestAttribute(request);

		request.getRequestDispatcher("newpost.jsp").forward(request, response);
	}

	private void processViewPostRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IArticleDAO articleDAO = daoFactory.getArticleDAO();
		
		String articleID = request.getParameter("articleID");
		Article article = articleDAO.getArticle(articleID);
		request.setAttribute("article", article);
		logger.info(article.getTitle());
		request.getRequestDispatcher("showpost.jsp").forward(request, response);
	}


	private void processListPostRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IArticleDAO articleDAO = daoFactory.getArticleDAO();
		
		String pageNumberStr = request.getParameter("pageNumber");
		int pageNumber = 1;
		if (pageNumberStr != null && !pageNumberStr.isEmpty()) {
			pageNumber = Integer.parseInt(pageNumberStr);
		}

		String pageSizeString = this.getServletContext().getInitParameter(
				"pageSize");
		int pageSize = Integer.valueOf(pageSizeString); // 分页大小
		int totalPosts = articleDAO.getArticleTotalCount(); // 总文章数
		int totalPages = totalPosts / pageSize + (totalPosts == 0 ? 1 : 0)
				+ ((totalPosts % pageSize) > 0 ? 1 : 0); // 计算得出的总页数

		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalPosts", totalPosts);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("totalPages", totalPages);

		List<Article> articleList = articleDAO
				.queryByPage(pageNumber, pageSize);

		logger.info("ListArticle get articleList size:{}", articleList.size());
		request.setAttribute("articleList", articleList);

		request.getRequestDispatcher("manage.jsp").forward(request, response);
	}
}
