package ltweb.service.impl;

import java.util.List;
import ltweb.entity.Video;
import ltweb.repository.VideoRepository;
import ltweb.repository.impl.VideoRepositoryImpl;
import ltweb.service.VideoService;

public class VideoServiceImpl implements VideoService {
    
    VideoRepository videoRepo = new VideoRepositoryImpl();

    @Override
    public void insert(Video video) { videoRepo.insert(video); }

    @Override
    public void update(Video video) { videoRepo.update(video); }

    @Override
    public void delete(String videoId) {
        try {
            videoRepo.delete(videoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Video findById(String videoId) { return videoRepo.findById(videoId); }

    @Override
    public List<Video> findAll() { return videoRepo.findAll(); }

    @Override
    public List<Video> findByTitle(String title) { return videoRepo.findByTitle(title); }
    
    @Override
    public List<Video> findByCategoryId(int categoryId) {
        return videoRepo.findByCategoryId(categoryId);
    }
    
    @Override
    public List<Video> findByUserId(int userId) {
        return videoRepo.findByUserId(userId);
    }
}