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

@WebServlet(urlPatterns = { "/manager/category/edit" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
				maxFileSize = 1024 * 1024 * 10, 
				maxRequestSize = 1024 * 1024 * 50)
public class ManagerCategoryEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Category category = cateService.findById(Integer.parseInt(id));
        
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        
        if (category == null || category.getUser().getUserId() != u.getUserId()) {
            resp.sendRedirect(req.getContextPath() + "/manager/home?error=Không có quyền sửa danh mục này");
            return;
        }
        
        req.setAttribute("cate", category);
        req.getRequestDispatcher("/view/manager/edit-category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        // Lấy lại User để kiểm tra lần nữa khi Submit
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");

        int id = Integer.parseInt(req.getParameter("categoryId"));
        Category category = cateService.findById(id);

        // Check quyền sở hữu
        if (category == null || category.getUser().getUserId() != u.getUserId()) {
            resp.sendRedirect(req.getContextPath() + "/manager/home?error=Không có quyền sửa");
            return;
        }

        // Cập nhật thông tin
        String categoryName = req.getParameter("categoryName");
        String categoryCode = req.getParameter("categoryCode");
        int status = Integer.parseInt(req.getParameter("status"));
        String oldImage = category.getImages();

        // Xử lý ảnh
        try {
            Part part = req.getPart("images");
            if (part != null && part.getSize() > 0) {
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String ext = filename.substring(filename.lastIndexOf(".") + 1);
                String fname = System.currentTimeMillis() + "." + ext;
                File uploadDir = new File(Constant.DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                part.write(Constant.DIR + File.separator + fname);
                category.setImages(fname);
            } else {
                category.setImages(oldImage);
            }
        } catch (Exception e) {
            category.setImages(oldImage);
        }

        category.setCategoryName(categoryName);
        category.setCategorycode(categoryCode);
        category.setStatus(status);

        cateService.update(category);
        resp.sendRedirect(req.getContextPath() + "/manager/home");
    }
}