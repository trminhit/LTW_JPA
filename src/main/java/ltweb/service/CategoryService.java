package ltweb.service;

import java.util.List;

import ltweb.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	Category findById(int id);

	void insert(Category category);

	void update(Category category);

	void delete(int id);


}
