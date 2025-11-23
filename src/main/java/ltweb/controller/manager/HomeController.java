package ltweb.controller.manager;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb.entity.Category;
import ltweb.entity.User;
import ltweb.service.CategoryService;
import ltweb.service.impl.CategoryServiceImpl;


@WebServlet(urlPatterns = {"/manager/home"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        
        if(u != null) {
            List<Category> list = cateService.findByUserId(u.getUserId());
            req.setAttribute("cateList", list);
        }
        req.getRequestDispatcher("/view/manager/home.jsp").forward(req, resp);
    }
}