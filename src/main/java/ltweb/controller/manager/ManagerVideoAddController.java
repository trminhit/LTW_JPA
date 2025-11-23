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

@WebServlet(urlPatterns = { "/manager/video/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
				maxFileSize = 1024 * 1024 * 10, 
				maxRequestSize = 1024 * 1024 * 50)
public class ManagerVideoAddController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	VideoService videoService = new VideoServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");

        // Chỉ lấy Category của chính Manager này để hiển thị
        List<Category> listCate = categoryService.findByUserId(u.getUserId());
        
        req.setAttribute("listCate", listCate);
        req.getRequestDispatcher("/view/manager/add-video.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        // Lấy dữ liệu form
        String videoId = req.getParameter("videoId");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int active = Integer.parseInt(req.getParameter("active"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        // Kiểm tra xem CategoryId này có thực sự của Manager không
        Category cate = categoryService.findById(categoryId);
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");

        if (cate == null || cate.getUser().getUserId() != u.getUserId()) {
            resp.sendRedirect(req.getContextPath() + "/manager/video/list?error=Quyền không hợp lệ");
            return;
        }

        // Xử lý ảnh 
        String poster = "";
        try {
            Part part = req.getPart("poster");
            if (part != null && part.getSize() > 0) {
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String ext = filename.substring(filename.lastIndexOf(".") + 1);
                String fname = System.currentTimeMillis() + "." + ext;
                File uploadDir = new File(Constant.DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                part.write(Constant.DIR + File.separator + fname);
                poster = fname;
            }
        } catch (Exception e) { e.printStackTrace(); }

        Video video = new Video();
        video.setVideoId(videoId);
        video.setTitle(title);
        video.setDescription(description);
        video.setActive(active);
        video.setPoster(poster);
        video.setViews(0);
        video.setCategory(cate);

        videoService.insert(video);
        resp.sendRedirect(req.getContextPath() + "/manager/video/list");
    }
}