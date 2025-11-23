package ltweb.controller.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ltweb.service.VideoService;
import ltweb.service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = { "/admin/video/delete" })
public class VideoDeleteController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            videoService.delete(id);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/video/list");
    }
}