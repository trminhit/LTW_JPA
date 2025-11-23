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

@WebServlet(urlPatterns = { "/manager/video/edit" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ManagerVideoEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    VideoService videoService = new VideoServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        
        if (id == null || id.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/manager/video/list");
            return;
        }

        //Lấy Video từ DB
        Video video = videoService.findById(id);
        
        // Lấy User hiện tại
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");

        // Nếu không tìm thấy video or người tạo ra Category của Video đó k phải là user hiện tại
        if (video == null || video.getCategory().getUser().getUserId() != u.getUserId()) {
            resp.sendRedirect(req.getContextPath() + "/manager/video/list?error=Bạn không có quyền sửa video này!");
            return;
        }

        List<Category> listCate = categoryService.findByUserId(u.getUserId());

        req.setAttribute("video", video);
        req.setAttribute("listCate", listCate);
        
        req.getRequestDispatcher("/view/manager/edit-video.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String videoId = req.getParameter("videoId");
        String title = req.getParameter("title");      
        String description = req.getParameter("description");
        int active = Integer.parseInt(req.getParameter("active"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        // Tìm video cũ trong DB
        Video video = videoService.findById(videoId);
        
        // Lấy User hiện tại
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");

        // Kiểm tra quyền sở hữu
        if(video == null || video.getCategory().getUser().getUserId() != u.getUserId()) {
             resp.sendRedirect(req.getContextPath() + "/manager/video/list?error=Quyền truy cập bị từ chối!");
             return;
        }
        
        // Kiểm tra Category mới được chọn có phải của mình không
        Category cate = categoryService.findById(categoryId);
        if(cate == null || cate.getUser().getUserId() != u.getUserId()){
             resp.sendRedirect(req.getContextPath() + "/manager/video/list?error=Danh mục không hợp lệ!");
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
                video.setPoster(fname); // Lưu tên ảnh mới
            } else {
                video.setPoster(oldPoster); // Giữ nguyên ảnh cũ
            }
        } catch (Exception e) {
            e.printStackTrace();
            video.setPoster(oldPoster);
        }

        // Cập nhật thông tin
        video.setTitle(title);              
        video.setDescription(description);
        video.setActive(active);
        video.setCategory(cate); 

        // Lưu xuống DB
        videoService.update(video);
        
        resp.sendRedirect(req.getContextPath() + "/manager/video/list");
    }
}