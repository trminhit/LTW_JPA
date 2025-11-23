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

@WebServlet(urlPatterns = { "/manager/category/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
				maxFileSize = 1024 * 1024 * 10, 
				maxRequestSize = 1024 * 1024 * 50)
public class ManagerCategoryAddController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/manager/add-category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String categoryName = req.getParameter("categoryName");
        String categoryCode = req.getParameter("categoryCode");
        int status = Integer.parseInt(req.getParameter("status"));
        String images = "";

        // Xử lý upload ảnh
        try {
            Part part = req.getPart("images");
            if (part != null && part.getSize() > 0) {
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String ext = filename.substring(filename.lastIndexOf(".") + 1);
                String fname = System.currentTimeMillis() + "." + ext;
                
                File uploadDir = new File(Constant.DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                
                part.write(Constant.DIR + File.separator + fname);
                images = fname;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategorycode(categoryCode);
        category.setStatus(status);
        category.setImages(images);

        // QUAN TRỌNG: Gán người tạo là User đang đăng nhập
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        category.setUser(u); 

        cateService.insert(category);
        resp.sendRedirect(req.getContextPath() + "/manager/home");
    }
}