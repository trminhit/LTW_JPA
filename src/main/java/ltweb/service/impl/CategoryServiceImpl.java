package ltweb.service.impl;

import java.util.List;
import ltweb.entity.Category;
import ltweb.repository.CategoryRepository;
import ltweb.repository.impl.CategoryRepositoryImpl;
import ltweb.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
    
    CategoryRepository categoryRepo = new CategoryRepositoryImpl();

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryRepo.findById(id);
    }

    @Override
    public void insert(Category category) {
        categoryRepo.insert(category);
    }

    @Override
    public void update(Category category) {
        categoryRepo.update(category);
    }

    @Override
    public void delete(int id) {
        try {
            categoryRepo.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Category> findByUserId(int userId) {
        return categoryRepo.findByUserId(userId);
    }

}