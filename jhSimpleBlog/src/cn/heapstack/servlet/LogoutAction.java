package cn.heapstack.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.simpleblog.domain.User;

public class LogoutAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8217857913068651996L;

	Logger logger = LoggerFactory.getLogger(LogoutAction.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("user");
		if( u != null )
		{
			logger.info("User:["+u.getUserId()+"] with nickName:["+u.getNickName()+"] logged out from session:["+session.getId()+"]");
			session.removeAttribute("user");
		}
		req.getRequestDispatcher("/Home").forward(req, resp);
	}
}
