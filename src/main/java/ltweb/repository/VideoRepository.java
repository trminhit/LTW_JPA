package ltweb.repository;

import java.util.List;

import ltweb.entity.Video;

public interface VideoRepository {

	void insert(Video video);

	void update(Video video);

	void delete(String videoId) throws Exception;

	Video findById(String videoId);

	List<Video> findAll();

	List<Video> findByTitle(String title);

	List<Video> findByCategoryId(int categoryId);

	List<Video> findByUserId(int userId);

}
