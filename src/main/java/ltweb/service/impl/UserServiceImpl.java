package ltweb.service.impl;

import java.sql.Date;
import ltweb.entity.User;
import ltweb.repository.UserRepository;
import ltweb.repository.impl.UserRepositoryImpl;
import ltweb.service.UserService;

public class UserServiceImpl implements UserService {

    UserRepository userRepo = new UserRepositoryImpl();

    @Override
    public User login(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(String email, String password, String username, String fullname, String phone) {
        // Kiểm tra tồn tại
        if (userRepo.checkExistUsername(username)) return false;
        if (userRepo.checkExistEmail(email)) return false;
        if (userRepo.checkExistPhone(phone)) return false;

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setFullname(fullname);
        user.setPassword(password);
        user.setRoleid(1); // Mặc định role là 1 (User)
        user.setPhone(phone);
        user.setCreateddate(date);

        userRepo.insert(user);
        return true;
    }
    @Override
    public void updatePassword(String email, String newPassword) {
        userRepo.changePassword(email, newPassword);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userRepo.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userRepo.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userRepo.checkExistPhone(phone);
    }
}