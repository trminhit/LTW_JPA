package ltweb.controller.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb.entity.Category;
import ltweb.entity.User;
import ltweb.entity.Video;
import ltweb.service.CategoryService;
import ltweb.service.VideoService;
import ltweb.service.impl.CategoryServiceImpl;
import ltweb.service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = { "/manager/video/list" })
public class ManagerVideoListController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    VideoService videoService = new VideoServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        
        String cateIdStr = req.getParameter("categoryId");
        List<Video> list = new ArrayList<>();
        
        if (cateIdStr != null) {
            // Xem theo category
            int cateId = Integer.parseInt(cateIdStr);
            Category cate = categoryService.findById(cateId);
            
            // Bảo mật: Kiểm tra xem Category này có phải của Manager không?
            if (cate != null && cate.getUser().getUserId() == u.getUserId()) {
                list = videoService.findByCategoryId(cateId);
                req.setAttribute("currentCategory", cate); 
            } else {
                // 0 sở hữu category -> báo lỗi
                req.setAttribute("error", "Bạn không có quyền truy cập danh mục này!");
            }
        }
        
        req.setAttribute("videoList", list);
        req.getRequestDispatcher("/view/manager/list-video.jsp").forward(req, resp);
    }
}