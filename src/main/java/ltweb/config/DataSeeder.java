package ltweb.config;

import java.sql.Date;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ltweb.entity.Category;
import ltweb.entity.User;
import ltweb.entity.Video;
import ltweb.repository.CategoryRepository;
import ltweb.repository.UserRepository;
import ltweb.repository.VideoRepository;
import ltweb.repository.impl.CategoryRepositoryImpl;
import ltweb.repository.impl.UserRepositoryImpl;
import ltweb.repository.impl.VideoRepositoryImpl;
import ltweb.service.UserService;
import ltweb.service.impl.UserServiceImpl;

@WebListener
public class DataSeeder implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserService userService = new UserServiceImpl();
        UserRepository userRepo = new UserRepositoryImpl();
        CategoryRepository cateRepo = new CategoryRepositoryImpl();
        VideoRepository videoRepo = new VideoRepositoryImpl();

        // ----------------------------------------------------------------
        // 1. TÀI KHOẢN ADMIN: NGUYỄN TRƯỜNG MINH (minhnt)
        // => Chuyên mục: PHIM BOM TẤN & CHIẾU RẠP
        // ----------------------------------------------------------------
        if (!userService.checkExistUsername("minhnt")) {
            User admin = new User();
            admin.setUsername("minhnt");
            admin.setPassword("12345");
            admin.setEmail("admin@ltweb.com");
            admin.setFullname("Nguyễn Trường Minh (Admin)");
            admin.setPhone("0999999999");
            admin.setRoleid(3); // Role Admin
            admin.setCreateddate(new Date(System.currentTimeMillis()));
            admin.setActive(true);
            userRepo.insert(admin);
            
            // --- Category: Phim Chiếu Rạp Hot ---
            Category cate1 = new Category();
            cate1.setCategoryName("Phim Chiếu Rạp Hot");
            cate1.setCategorycode("THEATER");
            cate1.setStatus(1);
            cate1.setImages("PhimChieuRap.jpg"); 
            cate1.setUser(admin);
            cateRepo.insert(cate1);

            // Video 1: Tử chiến trên không
            Video v1 = new Video();
            v1.setVideoId("MV001");
            v1.setTitle("Tử chiến trên không");
            v1.setDescription("Những màn không chiến đỉnh cao và kịch tính đến nghẹt thở.");
            v1.setPoster("TuChienTrenKhong.jpg"); 
            v1.setViews(5000);
            v1.setActive(1);
            v1.setCategory(cate1);
            videoRepo.insert(v1);

            // Video 2: Avengers: End Game
            Video v2 = new Video();
            v2.setVideoId("MV002");
            v2.setTitle("Avengers: End Game");
            v2.setDescription("Trận chiến cuối cùng của các siêu anh hùng Marvel chống lại Thanos.");
            v2.setPoster("EndGame.jpg"); // Tên ảnh theo yêu cầu
            v2.setViews(12000);
            v2.setActive(1);
            v2.setCategory(cate1);
            videoRepo.insert(v2);

            // Video 3: GODZILLA VS. KONG
            Video v3 = new Video();
            v3.setVideoId("MV003");
            v3.setTitle("GODZILLA VS. KONG");
            v3.setDescription("Cuộc đại chiến thế kỷ giữa hai quái vật khổng lồ.");
            v3.setPoster("KongVsGodzilla.jpg"); 
            v3.setViews(9500);
            v3.setActive(1);
            v3.setCategory(cate1);
            videoRepo.insert(v3);

            System.out.println("--- Đã tạo Admin & Dữ liệu Phim Chiếu Rạp Hot ---");
        }

        // ----------------------------------------------------------------
        // 2. MANAGER 1: ĐOÀN QUỐC MINH (manager1)
        // => Chuyên mục: HÀNH ĐỘNG & TÌNH CẢM
        // ----------------------------------------------------------------
        if (!userService.checkExistUsername("manager1")) {
            User manager = new User();
            manager.setUsername("manager1");
            manager.setPassword("12345");
            manager.setEmail("manager@ltweb.com");
            manager.setFullname("Đoàn Quốc Minh");
            manager.setPhone("0888888888");
            manager.setRoleid(2); // Role Manager
            manager.setCreateddate(new Date(System.currentTimeMillis()));
            manager.setActive(true);
            userRepo.insert(manager);
            
            // --- Category A: Phim Hành Động ---
            Category cateAction = new Category();
            cateAction.setCategoryName("Phim Hành Động");
            cateAction.setCategorycode("ACTION");
            cateAction.setStatus(1);
            cateAction.setImages("PhimHanhDong.jpg");
            cateAction.setUser(manager); 
            cateRepo.insert(cateAction);

            Video vAction1 = new Video();
            vAction1.setVideoId("ACT001");
            vAction1.setTitle("John Wick 4");
            vAction1.setDescription("Sát thủ John Wick đối đầu với Hội Tối Cao.");
            vAction1.setPoster("JW4.jpg");
            vAction1.setViews(5600);
            vAction1.setActive(1);
            vAction1.setCategory(cateAction);
            videoRepo.insert(vAction1);

            // --- Category B: Phim Tình Cảm ---
            Category cateRomance = new Category();
            cateRomance.setCategoryName("Phim Tình Cảm");
            cateRomance.setCategorycode("ROMANCE");
            cateRomance.setStatus(1);
            cateRomance.setImages("PhimTinhCam.jpg");
            cateRomance.setUser(manager); 
            cateRepo.insert(cateRomance);
            
            Video vRomance1 = new Video();
            vRomance1.setVideoId("ROM001");
            vRomance1.setTitle("Genie Make A Wish");
            vRomance1.setDescription("Phim xoay quanh một thần đèn (Kim Woo-bin) thức tỉnh sau ngàn năm và một cô gái lạnh lùng, vô cảm tên Ka Young (Bae Suzy) trở thành chủ nhân của chiếc đèn thần.");
            vRomance1.setPoster("Genie.jpg");
            vRomance1.setViews(3200);
            vRomance1.setActive(1);
            vRomance1.setCategory(cateRomance);
            videoRepo.insert(vRomance1);

            System.out.println("--- Đã tạo Manager 1 (Đoàn Quốc Minh) & Dữ liệu Phim Hành Động/Tình Cảm ---");
        }

        // ----------------------------------------------------------------
        // 3. MANAGER 2: DƯƠNG TRUNG MẠNH (manager2)
        // => Chuyên mục: KINH DỊ & HOẠT HÌNH
        // ----------------------------------------------------------------
        if (!userService.checkExistUsername("manager2")) {
            User manh = new User();
            manh.setUsername("manager2");
            manh.setPassword("12345");
            manh.setEmail("manhdt@ltweb.com");
            manh.setFullname("Dương Trung Mạnh");
            manh.setPhone("0912345678");
            manh.setRoleid(2); // Role Manager
            manh.setCreateddate(new Date(System.currentTimeMillis()));
            manh.setActive(true);
            userRepo.insert(manh);

            // --- Category C: Phim Kinh Dị ---
            Category cateHorror = new Category();
            cateHorror.setCategoryName("Phim Kinh Dị");
            cateHorror.setCategorycode("HORROR");
            cateHorror.setStatus(1);
            cateHorror.setImages("PhimKinhDi.jpg");
            cateHorror.setUser(manh); 
            cateRepo.insert(cateHorror);

            Video vHorror1 = new Video();
            vHorror1.setVideoId("HOR001");
            vHorror1.setTitle("TEE YOD: QUỶ ĂN TẠNG");
            vHorror1.setDescription("Phim kể về câu chuyện đầy ám ảnh của một linh hồn khát máu nhập vào cơ thể của một cô gái trẻ ở một ngôi làng hẻo lánh.");
            vHorror1.setPoster("TeeYod.jpg");
            vHorror1.setViews(4100);
            vHorror1.setActive(1);
            vHorror1.setCategory(cateHorror);
            videoRepo.insert(vHorror1);

            // --- Category D: Hoạt Hình (Anime) ---
            Category cateAnime = new Category();
            cateAnime.setCategoryName("Anime & Hoạt Hình");
            cateAnime.setCategorycode("ANIME");
            cateAnime.setStatus(1);
            cateAnime.setImages("Anime.jpg");
            cateAnime.setUser(manh); 
            cateRepo.insert(cateAnime);

            Video vAnime1 = new Video();
            vAnime1.setVideoId("ANI001");
            vAnime1.setTitle("Jujutsu Kaisen");
            vAnime1.setDescription("Đây là câu chuyện về nam sinh trung học tên là Yuuji Itadori sở hữu thể chất hoàn hảo thu hút các quái vật ăn thịt người.");
            vAnime1.setPoster("Jujutsu.jpg");
            vAnime1.setViews(7500);
            vAnime1.setActive(1);
            vAnime1.setCategory(cateAnime);
            videoRepo.insert(vAnime1);
            
            Video vAnime2 = new Video();
            vAnime2.setVideoId("ANI002");
            vAnime2.setTitle("Demon Slayer: Kimetsu no Yaiba");
            vAnime2.setDescription("Truyện kể về hành trình trở thành kiếm sĩ diệt quỷ của thiếu niên Kamado Tanjirō sau khi gia đình cậu bị quỷ sát hại và em gái Nezuko của cậu bị biến thành quỷ.");
            vAnime2.setPoster("kimetsu.jpg");
            vAnime2.setViews(9500);
            vAnime2.setActive(1);
            vAnime2.setCategory(cateAnime);
            videoRepo.insert(vAnime2);

            System.out.println("--- Đã tạo Manager 2 (Dương Trung Mạnh) & Dữ liệu Phim Kinh Dị/Anime ---");
        }

        // ----------------------------------------------------------------
        // 4. TÀI KHOẢN USER THƯỜNG (user)
        // ----------------------------------------------------------------
        if (!userService.checkExistUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setPassword("12345");
            user.setEmail("user@ltweb.com");
            user.setFullname("Lương Quang Minh (Khán Giả)");
            user.setPhone("0777777777");
            user.setRoleid(1); // Role User
            user.setCreateddate(new Date(System.currentTimeMillis()));
            user.setActive(true);
            userRepo.insert(user);
            System.out.println("--- Đã tạo User khán giả ---");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}