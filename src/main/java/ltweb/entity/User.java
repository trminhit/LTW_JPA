package ltweb.entity;


import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="users")
@NamedQuery(name="User.findAll", query="SELECT c FROM User c")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserId")
	private int userId;
	
	@Column(name="Email", columnDefinition = "NVARCHAR(50)")
	private String email;
	
	@Column(name="Fullname", columnDefinition = "NVARCHAR(50)")
	private String fullname;
	
	@Column(name="Username", columnDefinition = "NVARCHAR(50)")
	private String username;
	
	@Column(name="Password", columnDefinition = "NVARCHAR(50)")
	private String password;
	
	@Column(name="Avatar",columnDefinition="NVARCHAR(255)")
	private String avatar;
	
	@Column(name="RoleId")

	private int roleid;
	
	@Column(name="Phone", columnDefinition="VARCHAR(20)")
	private String phone;
	
	@Column(name="CreatedDate")
	private Date createddate;
	
	@Column(name="Active")
	private boolean active;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Category> categories;
}
