package ltweb.service;

import java.util.List;

import ltweb.entity.Video;

public interface VideoService {

	void insert(Video video);

	void update(Video video);

	void delete(String videoId);

	Video findById(String videoId);

	List<Video> findAll();

	List<Video> findByTitle(String title);

}
