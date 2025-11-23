package ltweb.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import ltweb.entity.Category;
import ltweb.entity.User;
import ltweb.service.CategoryService;
import ltweb.service.impl.CategoryServiceImpl;
import ltweb.utils.Constant;

@WebServlet(urlPatterns = { 
    "/admin/category/list", 
    "/admin/category/add", 
    "/admin/category/edit", 
    "/admin/category/delete"
})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
				maxFileSize = 1024 * 1024 * 10, 
				maxRequestSize = 1024 * 1024 * 50)
public class CategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        
        if (url.contains("list")) {
            findAll(req, resp);
        } else if (url.contains("add")) {
            req.getRequestDispatcher("/view/admin/add-category.jsp").forward(req, resp);
        } else if (url.contains("edit")) {
            showEdit(req, resp);
        } else if (url.contains("delete")) {
            delete(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("add")) {
            insert(req, resp);
        } else if (url.contains("edit")) {
            update(req, resp);
        }
    }

    // Các hàm xử lý

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> list = cateService.findAll();
        req.setAttribute("cateList", list);
        req.getRequestDispatcher("/view/admin/list-category.jsp").forward(req, resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            Category category = cateService.findById(Integer.parseInt(id));
            req.setAttribute("cate", category);
            req.getRequestDispatcher("/view/admin/edit-category.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id != null) {
            try {
                cateService.delete(Integer.parseInt(id));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Category category = new Category();
        String images = uploadFile(req, "images");
        category.setImages(images);
        
        category.setCategoryName(req.getParameter("categoryName"));
        category.setCategorycode(req.getParameter("categoryCode"));
        category.setStatus(Integer.parseInt(req.getParameter("status")));

        // Gán người tạo là Admin đang đăng nhập
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        category.setUser(u);

        cateService.insert(category);
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("categoryId"));
        Category category = cateService.findById(id);
        
        String oldImage = category.getImages();
        String newImage = uploadFile(req, "images");
        if (newImage != null && !newImage.isEmpty()) {
            category.setImages(newImage);
        } else {
            category.setImages(oldImage);
        }

        category.setCategoryName(req.getParameter("categoryName"));
        category.setCategorycode(req.getParameter("categoryCode"));
        category.setStatus(Integer.parseInt(req.getParameter("status")));

        cateService.update(category);
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }

    private String uploadFile(HttpServletRequest req, String partName) {
        try {
            Part part = req.getPart(partName);
            if (part != null && part.getSize() > 0) {
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String ext = filename.substring(filename.lastIndexOf(".") + 1);
                String fname = System.currentTimeMillis() + "." + ext;
                
                File uploadDir = new File(Constant.DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                
                part.write(Constant.DIR + File.separator + fname);
                return fname;
            }
        } catch (Exception e) { }
        return null;
    }
}