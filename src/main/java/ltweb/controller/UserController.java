package ltweb.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb.entity.User;

@WebServlet(urlPatterns = {"/user/home"})
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Kiểm tra đăng nhập
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("account") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		// Kiểm tra quyền
		User u = (User) session.getAttribute("account");
		
		// Giả sử RoleID = 1 là USER
		if (u.getRoleid() != 1) { 
			req.setAttribute("alert", "Bạn không có quyền truy cập!");
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
			return;
		}

		// Cho phép vào trang chủ User
		req.getRequestDispatcher("/view/user/home.jsp").forward(req, resp);
	}
}