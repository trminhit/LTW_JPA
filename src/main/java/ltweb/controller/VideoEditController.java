package ltweb.controller;

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
import jakarta.servlet.http.Part;
import ltweb.entity.Category;
import ltweb.entity.Video;
import ltweb.service.CategoryService;
import ltweb.service.VideoService;
import ltweb.service.impl.CategoryServiceImpl;
import ltweb.service.impl.VideoServiceImpl;
import ltweb.utils.Constant;

@WebServlet(urlPatterns = { "/admin/video/edit" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class VideoEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    VideoService videoService = new VideoServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        
        // Kiểm tra nếu id null thì quay về danh sách
        if (id == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/video/list");
            return;
        }

        Video video = videoService.findById(id);
        List<Category> listCate = categoryService.findAll();

        req.setAttribute("video", video);
        req.setAttribute("listCate", listCate);
        
        req.getRequestDispatcher("/view/admin/edit-video.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String videoId = req.getParameter("videoId");
        String title = req.getParameter("title");      
        String description = req.getParameter("description");
        
        int active = 0;
        if(req.getParameter("active") != null) {
            active = Integer.parseInt(req.getParameter("active"));
        }
        
        int categoryId = 0;
        if(req.getParameter("categoryId") != null) {
            categoryId = Integer.parseInt(req.getParameter("categoryId"));
        }

        // Tìm video cũ
        Video video = videoService.findById(videoId);
        
        // Nếu không tìm thấy video, quay về danh sách
        if(video == null) {
             resp.sendRedirect(req.getContextPath() + "/admin/video/list");
             return;
        }

        String oldPoster = video.getPoster();

        // Xử lý upload ảnh 
        try {
            Part part = req.getPart("poster"); 
            if (part != null && part.getSize() > 0) {
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String ext = filename.substring(filename.lastIndexOf(".") + 1);
                String fname = System.currentTimeMillis() + "." + ext;
                
                File uploadDir = new File(Constant.DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                
                part.write(Constant.DIR + File.separator + fname);
                video.setPoster(fname); // Lưu ảnh mới
            } else {
                video.setPoster(oldPoster); // Giữ ảnh cũ
            }
        } catch (Exception e) {
            e.printStackTrace();
            video.setPoster(oldPoster);
        }

        // Cập nhật thông tin vào đối tượng
        video.setTitle(title);              
        video.setDescription(description);
        video.setActive(active);
        
        Category cate = categoryService.findById(categoryId);
        video.setCategory(cate);

        // Lưu xuống DB
        videoService.update(video);
        
        resp.sendRedirect(req.getContextPath() + "/admin/video/list");
    }
}