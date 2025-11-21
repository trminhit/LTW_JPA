package ltweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class UserModel {

	private int id;

	private String email;

	private String fullname;

	private String username;

	private String password;
}
