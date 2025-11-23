package ltweb.controller.manager;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb.entity.User;
import ltweb.entity.Video;
import ltweb.service.VideoService;
import ltweb.service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = { "/manager/video/delete" })
public class ManagerVideoDeleteController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        
        if (id != null) {
            // Tìm video
            Video video = videoService.findById(id);
            
            // Lấy User hiện tại
            HttpSession session = req.getSession();
            User u = (User) session.getAttribute("account");
            
            // Kiểm tra quyền sở hữu
            if (video != null && video.getCategory().getUser().getUserId() == u.getUserId()) {
                try {
                    // Đúng chủ -> Xóa
                    videoService.delete(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Không đúng chủ -> Không làm gì
                System.out.println("User " + u.getUsername() + " cố tình xóa video không chính chủ: " + id);
            }
        }
        // Quay về danh sách
        resp.sendRedirect(req.getContextPath() + "/manager/video/list");
    }
}