package ltweb.controller.admin;

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

@WebServlet(urlPatterns = { 
    "/admin/video/list", 
    "/admin/video/add", 
    "/admin/video/edit", 
    "/admin/video/delete" 
})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
				maxFileSize = 1024 * 1024 * 10, 
				maxRequestSize = 1024 * 1024 * 50)
public class VideoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    VideoService videoService = new VideoServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        
        if (url.contains("list")) {
            findAll(req, resp);
        } else if (url.contains("add")) {
            // Load danh mục để hiển thị trong select box
            List<Category> listCate = categoryService.findAll();
            req.setAttribute("listCate", listCate);
            req.getRequestDispatcher("/view/admin/add-video.jsp").forward(req, resp);
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

    // Các hàm xử lý

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        String categoryIdStr = req.getParameter("categoryId");
        List<Video> list;
        
        if (keyword != null && !keyword.isEmpty()) {
            list = videoService.findByTitle(keyword);
        } else if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            try {
                list = videoService.findByCategoryId(Integer.parseInt(categoryIdStr));
            } catch (Exception e) {
                list = videoService.findAll();
            }
        } else {
            list = videoService.findAll();
        }
        
        req.setAttribute("videoList", list);
        req.setAttribute("keyword", keyword);
        req.getRequestDispatcher("/view/admin/list-video.jsp").forward(req, resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Video video = videoService.findById(id);
        
        if (video == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/video/list");
            return;
        }

        List<Category> listCate = categoryService.findAll();
        req.setAttribute("video", video);
        req.setAttribute("listCate", listCate);
        req.getRequestDispatcher("/view/admin/edit-video.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id != null) {
            try {
                videoService.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/admin/video/list");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Video video = new Video();
        
        String poster = uploadFile(req, "poster");
        video.setPoster(poster);
        
        video.setVideoId(req.getParameter("videoId"));
        video.setTitle(req.getParameter("title"));
        video.setDescription(req.getParameter("description"));
        video.setActive(Integer.parseInt(req.getParameter("active")));
        video.setViews(0);

        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Category cate = categoryService.findById(categoryId);
        video.setCategory(cate);

        videoService.insert(video);
        resp.sendRedirect(req.getContextPath() + "/admin/video/list");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String videoId = req.getParameter("videoId");
        Video video = videoService.findById(videoId);
        
        if (video == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/video/list");
            return;
        }

        String oldPoster = video.getPoster();
        String newPoster = uploadFile(req, "poster");
        if (newPoster != null) {
            video.setPoster(newPoster);
        } else {
            video.setPoster(oldPoster);
        }

        video.setTitle(req.getParameter("title"));
        video.setDescription(req.getParameter("description"));
        video.setActive(Integer.parseInt(req.getParameter("active")));

        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Category cate = categoryService.findById(categoryId);
        video.setCategory(cate);

        videoService.update(video);
        resp.sendRedirect(req.getContextPath() + "/admin/video/list");
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