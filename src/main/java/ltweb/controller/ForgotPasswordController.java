package ltweb.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb.entity.User;
import ltweb.service.UserService;
import ltweb.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        
        User user = userService.findByEmail(email);
        
        if (user == null) {
            // Email không tồn tại
            req.setAttribute("alert", "Email không tồn tại trong hệ thống!");
            req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
        } else {
            // Email tồn tại -> Lưu vào session để dùng ở bước Reset
            HttpSession session = req.getSession();
            session.setAttribute("resetEmail", email);
            
            // Chuyển sang trang đặt lại mật khẩu
            resp.sendRedirect(req.getContextPath() + "/reset-password");
        }
    }
}