package ltweb.repository.impl;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import ltweb.config.JPAConfig;
import ltweb.entity.Video;
import ltweb.repository.VideoRepository;

public class VideoRepositoryImpl implements VideoRepository {

    @Override
    public void insert(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(video);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            enma.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(video);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            enma.close();
        }
    }

    @Override
    public void delete(String videoId) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Video video = enma.find(Video.class, videoId);
            if (video != null) {
                enma.remove(video);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public Video findById(String videoId) {
        EntityManager enma = JPAConfig.getEntityManager();
        return enma.find(Video.class, videoId);
    }

    @Override
    public List<Video> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
        return query.getResultList();
    }

    @Override
    public List<Video> findByTitle(String title) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v WHERE v.title LIKE :title";
        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }
    
    @Override
    public List<Video> findByCategoryId(int categoryId) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT v FROM Video v WHERE v.category.categoryId = :categoryId";
            TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
            query.setParameter("categoryId", categoryId);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }
    
    @Override
    public List<Video> findByUserId(int userId) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            // Lấy Video mà Category của nó thuộc về User có ID tương ứng
            String jpql = "SELECT v FROM Video v WHERE v.category.user.userId = :userId";
            TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }
}