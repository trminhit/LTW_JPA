package ltweb.controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ltweb.entity.Category;
import ltweb.entity.Video;
import ltweb.service.CategoryService;
import ltweb.service.VideoService;
import ltweb.service.impl.CategoryServiceImpl;
import ltweb.service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = { "/video" }) 
public class UserVideoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    VideoService videoService = new VideoServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cateId = req.getParameter("categoryId");
        
        List<Video> listVideo;
        Category category = null;

        if (cateId != null) {
            // Lấy danh sách video theo danh mục
            listVideo = videoService.findByCategoryId(Integer.parseInt(cateId));
            // Lấy thông tin danh mục để hiển thị tiêu đề
            category = categoryService.findById(Integer.parseInt(cateId));
        } else {
            listVideo = videoService.findAll();
        }

        req.setAttribute("videoList", listVideo);
        req.setAttribute("category", category);
        
        // Chuyển sang trang hiển thị danh sách phim
        req.getRequestDispatcher("/view/user/video-list.jsp").forward(req, resp);
    }
}