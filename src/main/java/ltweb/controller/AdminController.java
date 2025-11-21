package ltweb.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb.entity.User;

@WebServlet(urlPatterns = {"/admin/home"})
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        // Kiểm tra đăng nhập
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Kiểm tra quyền (Authorization)
        User u = (User) session.getAttribute("account");
        if (u.getRoleid() != 3) { // là ADMIN
            // Nếu không phải Admin, báo lỗi hoặc đẩy về trang của họ
            req.setAttribute("alert", "Bạn không có quyền truy cập trang Admin!");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp); 
            return;
        }

        // Hợp lệ -> Cho phép truy cập
        req.getRequestDispatcher("/view/admin/home.jsp").forward(req, resp);
    }
}