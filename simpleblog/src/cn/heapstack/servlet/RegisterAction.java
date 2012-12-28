package cn.heapstack.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IUserDAO;
import cn.heapstack.simpleblog.domain.User;

public class RegisterAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1657488094378792992L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String email = req.getParameter("email");
		String nickName = req.getParameter("nickName");
		String password = req.getParameter("password");

		IUserDAO dao = DAOFactory.getDAOFactory().getUserDAO();
		User u = new User();

		u.setUserId(userId);
		u.setEmail(email);
		u.setNickName(nickName);
		u.setPassword(password);

		dao.addUser(u);

		/*
		 * HttpSession session = req.getSession(); session.setAttribute("User",u);
		 */
		req.getRequestDispatcher("/login.jsp").forward(req, resp);

	}

}
