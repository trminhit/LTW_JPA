package ltweb.repository;

import java.util.List;
import ltweb.entity.Category;

public interface CategoryRepository {
    List<Category> findAll();
    
    Category findById(int id);
    
    void insert(Category category);
    
    void update(Category category);
    
    void delete(int id) throws Exception;

	List<Category> findByUserId(int userId);
    
}