package ltweb.controller.manager;

import java.io.IOException;
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

@WebServlet(urlPatterns = { "/manager/category/delete" })
public class ManagerCategoryDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            int cateId = Integer.parseInt(id);
            Category category = cateService.findById(cateId);
            
            HttpSession session = req.getSession();
            User u = (User) session.getAttribute("account");
            
            if (category != null && category.getUser().getUserId() == u.getUserId()) {
                try {
                    cateService.delete(cateId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Không có quyền xóa category id: " + id);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/manager/home");
    }
}