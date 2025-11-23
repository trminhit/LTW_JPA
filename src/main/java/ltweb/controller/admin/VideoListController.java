package ltweb.controller.admin;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ltweb.entity.Video;
import ltweb.service.VideoService;
import ltweb.service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = { "/admin/video/list" }) 
public class VideoListController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        String categoryIdStr = req.getParameter("categoryId"); // Lấy ID danh mục từ URL
        
        List<Video> list;
        
        // Logic lọc dữ liệu
        if (keyword != null && !keyword.isEmpty()) {
            // Ưu tiên tìm theo từ khóa
            list = videoService.findByTitle(keyword);
        } else if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            // Nếu có categoryId -> Lọc video theo danh mục
            try {
                int categoryId = Integer.parseInt(categoryIdStr);
                list = videoService.findByCategoryId(categoryId);
            } catch (NumberFormatException e) {
                // Nếu ID không phải số thì lấy tất cả
                list = videoService.findAll();
            }
        } else {
            list = videoService.findAll();
        }
        
        req.setAttribute("videoList", list);
        req.setAttribute("keyword", keyword);
        
        req.getRequestDispatcher("/view/admin/list-video.jsp").forward(req, resp);
    }
}