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

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }
        
        req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        String re_password = req.getParameter("re_password");

        String alertMsg = "";

        if (!password.equals(re_password)) {
            alertMsg = "Mật khẩu nhập lại không khớp!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
            return;
        }

        if (userService.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
            return;
        }

        if (userService.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
            return;
        }
        
        // Gọi service đăng ký
        boolean isSuccess = userService.register(email, password, username, fullname, phone);

        if (isSuccess) {
            // Chuyển hướng về login sau khi đăng ký thành công
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "Lỗi hệ thống!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
        }
    }
}