package cn.heapstack.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IUserDAO;
import cn.heapstack.simpleblog.domain.User;
import cn.heapstack.simpleblog.utils.bean.Message;
import cn.heapstack.simpleblog.utils.bean.MessageStyle;

public class LoginAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1929349872710234569L;
	Logger logger = LoggerFactory.getLogger(LoginAction.class);
	DAOFactory daoFactory = DAOFactory.getDAOFactory();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/Home").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		IUserDAO userDAO = daoFactory.getUserDAO();
		
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		Message m = new Message();
		boolean isExist = userDAO.checkExist(userId);
		if( !isExist )
		{
			logger.error("User does not exist");
			m.setContent("User does not exist");
			m.setStyle(MessageStyle.errors);
			
			req.setAttribute("message", m);
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		else
		{
			User u = userDAO.getUser(userId);
			if( !u.getPassword().equals(password) )
			{
				logger.error("Password not correct");
				m.setContent("Password not correct");
				m.setStyle(MessageStyle.errors);
				
				req.setAttribute("message", m);
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
			else
			{
				HttpSession session = req.getSession();
				session.setAttribute("user", u);
				logger.info("User:["+u.getUserId()+"] with nickName:["+u.getNickName()+"] logged in with session:["+session.getId()+"]");
				
				m.setContent("User:["+u.getUserId()+"] with nickName:["+u.getNickName()+"] logged in with session:["+session.getId()+"]");
				m.setStyle(MessageStyle.info);
				
				req.setAttribute("message", m);
				
				req.getRequestDispatcher("/Posts").forward(req, resp);
				//resp.sendRedirect("Posts");//This redirect happens on client side
			}
		}
	}
}
