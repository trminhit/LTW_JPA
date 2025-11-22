package ltweb.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data 
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
	
	@Column(name="Categorycode", columnDefinition = "NVARCHAR(500) NULL")
	private String categorycode;
	
	@Column(name="Status")
	private int status;
	
	
	@ManyToOne
	@JoinColumn(name = "UserID")  
	private User user;
	
	// Thêm cascade = CascadeType.ALL: khi xóa Category thì xóa luôn Video thuộc về nó
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Video> videos;
	


}
