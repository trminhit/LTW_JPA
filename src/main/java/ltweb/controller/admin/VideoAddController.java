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

@WebServlet(urlPatterns = { "/admin/video/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class VideoAddController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	VideoService videoService = new VideoServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Load danh sách Category để hiển thị trong select
		List<Category> listCate = categoryService.findAll();
		req.setAttribute("listCate", listCate);
		req.getRequestDispatcher("/view/admin/add-video.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// Lấy thông tin từ form
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String videoId = req.getParameter("videoId");
		int active = Integer.parseInt(req.getParameter("active"));
		int categoryId = Integer.parseInt(req.getParameter("categoryId"));

		// Xử lý upload Poster
		String poster = "";
		try {
			Part part = req.getPart("poster");
			if (part != null && part.getSize() > 0) {
				String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				String ext = filename.substring(filename.lastIndexOf(".") + 1);
				String fname = System.currentTimeMillis() + "." + ext;

				File uploadDir = new File(Constant.DIR);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs();
				}

				part.write(Constant.DIR + File.separator + fname);
				poster = fname;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Video video = new Video();
		video.setVideoId(videoId);
		video.setTitle(title);
		video.setDescription(description);
		video.setActive(active);
		video.setPoster(poster);
		video.setViews(0); 

		// Set Category cho Video
		Category cate = categoryService.findById(categoryId);
		video.setCategory(cate);

		videoService.insert(video);
		resp.sendRedirect(req.getContextPath() + "/admin/video/list");
	}
}