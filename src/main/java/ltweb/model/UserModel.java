package ltweb.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class UserModel {

	private int userId; 

	private String username;

	private String password;

	private String email;

	private String fullname;

	private String phone;

	private String avatar;

	private int roleid;

	private Date createddate;

	private boolean active;
}
