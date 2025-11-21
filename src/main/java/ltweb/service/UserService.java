package ltweb.service;

import ltweb.entity.User;

public interface UserService {

	User login(String username, String password);

	boolean register(String email, String password, String username, String fullname, String phone);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);

	void updatePassword(String email, String newPassword);

	User findByEmail(String email);

}
