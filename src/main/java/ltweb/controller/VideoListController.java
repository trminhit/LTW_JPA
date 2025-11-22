package ltweb.controller;

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
        List<Video> list;
        
        if (keyword != null && !keyword.isEmpty()) {
            // Nếu có từ khóa tìm kiếm -> Gọi hàm search
            list = videoService.findByTitle(keyword);
        } else {
            // Nếu không -> Lấy tất cả
            list = videoService.findAll();
        }
        
        req.setAttribute("videoList", list);
        req.setAttribute("keyword", keyword); // Giữ lại từ khóa ở ô input
        
        req.getRequestDispatcher("/view/admin/list-video.jsp").forward(req, resp);
    }
}