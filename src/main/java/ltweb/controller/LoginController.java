package ltweb.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb.entity.User;
import ltweb.service.UserService;
import ltweb.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra nếu đã đăng nhập thì chuyển thẳng đến trang waiting
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }
        
        // Kiểm tra Cookie (nếu có chức năng Remember Me)
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = req.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/waiting");
                    return;
                }
            }
        }

        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Lấy tham số từ form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        // Xử lý Remember Me 
        String remember = req.getParameter("remember");
        boolean isRememberMe = "on".equals(remember);

        // Kiểm tra dữ liệu input
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được để trống!");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
            return;
        }

        // Gọi Service kiểm tra đăng nhập
        User user = userService.login(username, password);

        // Xử lý kết quả
        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);

            // Lưu cookie nếu user chọn Remember Me
            if (isRememberMe) {
                saveRememberMeCookie(resp, username);
            }

            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng!");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        }
    }

    // Hàm phụ trợ lưu cookie
    private void saveRememberMeCookie(HttpServletResponse resp, String username) {
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(30 * 60); // 30 phút
        resp.addCookie(cookie);
    }
}