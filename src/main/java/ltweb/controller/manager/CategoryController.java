package ltweb.controller.manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

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
    "/manager/category/add", 
    "/manager/category/edit", 
    "/manager/category/delete" 
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
        
        if (url.contains("add")) {
            req.getRequestDispatcher("/view/manager/add-category.jsp").forward(req, resp);
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

    // Hàm xử lý logic

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Category category = cateService.findById(Integer.parseInt(id));
        
        // Kiểm tra quyền
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        
        if (category == null || category.getUser().getUserId() != u.getUserId()) {
            resp.sendRedirect(req.getContextPath() + "/manager/home?error=Không có quyền sửa danh mục này");
            return;
        }
        
        req.setAttribute("cate", category);
        req.getRequestDispatcher("/view/manager/edit-category.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
            }
        }
        resp.sendRedirect(req.getContextPath() + "/manager/home");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Category category = new Category();
        
        String images = uploadFile(req, "images");
        category.setImages(images);
        
        category.setCategoryName(req.getParameter("categoryName"));
        category.setCategorycode(req.getParameter("categoryCode"));
        category.setStatus(Integer.parseInt(req.getParameter("status")));

        // Gán người tạo là Manager đang đăng nhập
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        category.setUser(u); 

        cateService.insert(category);
        resp.sendRedirect(req.getContextPath() + "/manager/home");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");

        int id = Integer.parseInt(req.getParameter("categoryId"));
        Category category = cateService.findById(id);

        // Check quyền
        if (category == null || category.getUser().getUserId() != u.getUserId()) {
            resp.sendRedirect(req.getContextPath() + "/manager/home?error=Không có quyền sửa");
            return;
        }

        String oldImage = category.getImages();
        String newImage = uploadFile(req, "images");
        if (newImage != null) {
            category.setImages(newImage);
        } else {
            category.setImages(oldImage);
        }

        category.setCategoryName(req.getParameter("categoryName"));
        category.setCategorycode(req.getParameter("categoryCode"));
        category.setStatus(Integer.parseInt(req.getParameter("status")));

        cateService.update(category);
        resp.sendRedirect(req.getContextPath() + "/manager/home");
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