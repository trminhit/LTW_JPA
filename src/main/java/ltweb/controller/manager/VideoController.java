package ltweb.controller.manager;

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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import ltweb.entity.Category;
import ltweb.entity.User;
import ltweb.entity.Video;
import ltweb.service.CategoryService;
import ltweb.service.VideoService;
import ltweb.service.impl.CategoryServiceImpl;
import ltweb.service.impl.VideoServiceImpl;
import ltweb.utils.Constant;

@WebServlet(urlPatterns = { 
    "/manager/video/list", 
    "/manager/video/add", 
    "/manager/video/edit", 
    "/manager/video/delete" 
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
            showAdd(req, resp);
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

    // Hàm xử lý logic

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        
        String cateIdStr = req.getParameter("categoryId");
        List<Video> list;
        
        if (cateIdStr != null) {
            int cateId = Integer.parseInt(cateIdStr);
            Category cate = categoryService.findById(cateId);
            
            // Bảo mật: Chỉ xem nếu Category thuộc về Manager này
            if (cate != null && cate.getUser().getUserId() == u.getUserId()) {
                list = videoService.findByCategoryId(cateId);
                req.setAttribute("currentCategory", cate); 
            } else {
                req.setAttribute("error", "Bạn không có quyền truy cập danh mục này!");
                list = videoService.findByUserId(u.getUserId()); // Fallback về list của mình
            }
        } else {
            // Mặc định lấy tất cả video của Manager này
            list = videoService.findByUserId(u.getUserId());
        }
        
        req.setAttribute("videoList", list);
        req.getRequestDispatcher("/view/manager/list-video.jsp").forward(req, resp);
    }

    private void showAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        
        // Chỉ load Category của Manager này vào dropdown
        List<Category> listCate = categoryService.findByUserId(u.getUserId());
        req.setAttribute("listCate", listCate);
        req.getRequestDispatcher("/view/manager/add-video.jsp").forward(req, resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Video video = videoService.findById(id);
        
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");

        // Check quyền: Video -> Category -> User
        if (video == null || video.getCategory().getUser().getUserId() != u.getUserId()) {
            resp.sendRedirect(req.getContextPath() + "/manager/video/list?error=Không có quyền sửa");
            return;
        }

        List<Category> listCate = categoryService.findByUserId(u.getUserId());
        req.setAttribute("video", video);
        req.setAttribute("listCate", listCate);
        req.getRequestDispatcher("/view/manager/edit-video.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Video video = videoService.findById(id);
            HttpSession session = req.getSession();
            User u = (User) session.getAttribute("account");

            if (video != null && video.getCategory().getUser().getUserId() == u.getUserId()) {
                try {
                    videoService.delete(id);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/manager/video/list");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");

        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Category cate = categoryService.findById(categoryId);

        // Bảo mật: Check category
        if (cate == null || cate.getUser().getUserId() != u.getUserId()) {
            resp.sendRedirect(req.getContextPath() + "/manager/video/list?error=Quyền không hợp lệ");
            return;
        }

        Video video = new Video();
        String poster = uploadFile(req, "poster");
        video.setPoster(poster);
        
        video.setVideoId(req.getParameter("videoId"));
        video.setTitle(req.getParameter("title"));
        video.setDescription(req.getParameter("description"));
        video.setActive(Integer.parseInt(req.getParameter("active")));
        video.setViews(0);
        video.setCategory(cate);

        videoService.insert(video);
        resp.sendRedirect(req.getContextPath() + "/manager/video/list");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");

        String videoId = req.getParameter("videoId");
        Video video = videoService.findById(videoId);
        
        // Bảo mật
        if (video == null || video.getCategory().getUser().getUserId() != u.getUserId()) {
            resp.sendRedirect(req.getContextPath() + "/manager/video/list?error=Quyền truy cập bị từ chối");
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
        // Check category mới (nếu đổi danh mục)
        if (cate != null && cate.getUser().getUserId() == u.getUserId()) {
            video.setCategory(cate);
        }

        videoService.update(video);
        resp.sendRedirect(req.getContextPath() + "/manager/video/list");
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