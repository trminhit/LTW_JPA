package ltweb.repository;

import ltweb.entity.User;

public interface UserRepository {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhone(String phone); 
    void insert(User user);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
	void changePassword(String email, String newPassword);
}