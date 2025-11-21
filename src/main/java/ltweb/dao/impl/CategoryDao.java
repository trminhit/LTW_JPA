package ltweb.dao.impl;

import ltweb.entity.Category;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import ltweb.config.JPAConfig;
public class CategoryDao {
	void insert(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		finally {
			enma.close();
		}
	}
	void update(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		finally {
			enma.close();
		}
	}
	void delete(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.remove(category);
			trans.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		finally {
			enma.close();
		}
	}
	
	public Category findById(int cateid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Category category = enma.find(Category.class, cateid);
		return category;
	}
	public List<Category> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query= enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
		}
}
