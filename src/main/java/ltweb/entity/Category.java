package ltweb.entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name="category")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CategoryID")
	private int categoryId;
	
	@Column(name="CategoryName", columnDefinition = "NVARCHAR(50) NOT NULL")
	private String categoryName;
	
	@Column(name="Images", columnDefinition = "NVARCHAR(500) NULL")
	private String images;
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "UserID")  
	private User user;
	


}
