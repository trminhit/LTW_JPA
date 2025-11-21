package ltweb.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb.service.UserService;
import ltweb.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/reset-password")
public class ResetPasswordController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        // Nếu chưa qua bước ForgotPassword (chưa có email trong session) -> đá về login
        if (session == null || session.getAttribute("resetEmail") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.getRequestDispatcher("/view/reset-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("resetEmail");
        
        if (email == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        
        if (!password.equals(repassword)) {
            req.setAttribute("alert", "Mật khẩu nhập lại không khớp!");
            req.getRequestDispatcher("/view/reset-password.jsp").forward(req, resp);
            return;
        }
        
        // Gọi service cập nhật mật khẩu
        userService.updatePassword(email, password);
        
        // Xóa session email reset
        session.removeAttribute("resetEmail");
        
        // Chuyển về trang login với thông báo (có thể thêm param alert để login.jsp hiển thị)
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}