package ltweb.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb.entity.User;

@WebServlet(urlPatterns = {"/manager/home"})
public class ManagerController extends HttpServlet {
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
		
		if (u.getRoleid() != 2) { 
			// Nếu không phải Manager -> Báo lỗi hoặc đá về trang đăng nhập
			req.setAttribute("alert", "Bạn không có quyền truy cập vào trang Manager!");
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
			return;
		}

		// Nếu đúng quyền -> Cho phép vào trang chủ Manager
		req.getRequestDispatcher("/view/manager/home.jsp").forward(req, resp);
	}
}