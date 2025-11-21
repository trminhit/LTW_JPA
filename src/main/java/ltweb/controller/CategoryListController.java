package ltweb.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ltweb.entity.Category;
import ltweb.service.CategoryService;
import ltweb.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/category/list" }) 
public class CategoryListController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> list = cateService.findAll();
        
        req.setAttribute("cateList", list);
        
        req.getRequestDispatcher("/view/admin/list-category.jsp").forward(req, resp);
    }
}