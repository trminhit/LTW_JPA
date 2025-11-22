package ltweb.filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb.entity.User;

@WebFilter(urlPatterns = "/admin/*") 
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        // Lấy session hiện tại
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("account") : null;

        // Kiểm tra đăng nhập
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login?alert=Vui lòng đăng nhập!");
            return;
        }

        // Kiểm tra Role
        if (user.getRoleid() != 3) {
            resp.sendRedirect(req.getContextPath() + "/login?alert=Bạn không có quyền Admin!");
            return;
        }
        // Hợp lệ -> Cho đi tiếp
        chain.doFilter(request, response);
    }
}