package cn.heapstack.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.ICategoryDAO;
import cn.heapstack.simpleblog.demodaoImpl.CategoryDAODemo;
import cn.heapstack.simpleblog.domain.Category;

public class CategoryAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1346285721415992229L;
	
	private static Logger logger = LoggerFactory.getLogger(Category.class);

	private ArrayList<Category> getCategoryList() {
		ArrayList<Category> categoryList = new ArrayList<Category>();

		HashMap<String, Category> categoryMap = CategoryDAODemo.categoryMap;
		Iterator<String> itr = categoryMap.keySet().iterator();
		while (itr.hasNext()) {
			categoryList.add(categoryMap.get(itr.next()));
		}
		return categoryList;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		String categoryName = req.getParameter("categoryName");
		
		ICategoryDAO dao = DAOFactory.getDAOFactory().getCategoryDAO();
		if (method != null) 
		{
			if (method.equals("edit")) {
				Category c = dao.getCategory(categoryName);
				req.setAttribute("categoryName", categoryName);
				req.setAttribute("categoryComments", c.getCategoryComments());

			}
			if (method.equals("delete")) {
				dao.deleteCategory(categoryName);
				logger.info("CategoryName(PK) : {} deleted" ,categoryName);
			}
			// TODO: set success message
		}
		ArrayList<Category> categoryList = getCategoryList();
		req.setAttribute("categoryList", categoryList);

		req.getRequestDispatcher("newcategory.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String categoryName = req.getParameter("categoryName");
		String categoryComments = req.getParameter("categoryComments");
		String postdata = "";

		ICategoryDAO dao = new CategoryDAODemo();
		if (dao.isCategoryExist(categoryName)) {
			Category c = new Category(categoryName, categoryComments);
			dao.updateCategory(c);
			postdata = "CategoryName : " + categoryName + " updated";

		} else {
			Category c = new Category(categoryName, categoryComments);
			dao.addCategory(c);

			postdata = "CategoryName : " + categoryName + " created";
		}

		logger.info(postdata);

		ArrayList<Category> categoryList = getCategoryList();
		req.setAttribute("categoryList", categoryList);
		req.getRequestDispatcher("newcategory.jsp").forward(req, resp);
	}
}
