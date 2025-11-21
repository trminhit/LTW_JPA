package ltweb.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ltweb.entity.User;
import ltweb.service.UserService;
import ltweb.service.impl.UserServiceImpl;
import ltweb.repository.impl.UserRepositoryImpl;
import ltweb.repository.UserRepository;

@WebListener // Annotation này giúp Tomcat tự chạy class này khi start
public class DataSeeder implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Kiểm tra xem tài khoản admin đã tồn tại chưa
        UserService userService = new UserServiceImpl();
        
        // Dùng Repository để insert trực tiếp
        UserRepository userRepo = new UserRepositoryImpl();

        if (!userService.checkExistUsername("minhnt")) {
            System.out.println("--- Đang khởi tạo dữ liệu Admin mặc định ---");
            
            User admin = new User();
            admin.setUsername("minhnt");
            admin.setPassword("12345");
            admin.setEmail("admin@ltweb.com");
            admin.setFullname("Nguyễn Trường Minh");
            admin.setPhone("0999999999");
            admin.setRoleid(3); // Role 3 = ADMIN
            admin.setCreateddate(new java.sql.Date(System.currentTimeMillis()));
            
            userRepo.insert(admin);
            
            System.out.println("--- Đã tạo tài khoản Admin thành công: minhnt / 12345 ---");
        } else {
            System.out.println("--- Tài khoản Admin đã tồn tại, bỏ qua bước khởi tạo ---");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Không cần làm gì khi tắt server
    }
}