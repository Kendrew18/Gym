package com.gym.gymapp.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gym.gymapp.model.singleVideo;

public interface singleVideoRepositorys extends JpaRepository<singleVideo, Long> {
    
    @Query(value = "SELECT LAST_INSERT_ID() AS new_id; ", nativeQuery = true)
    public Long getlastId();

    @Query(value = "SELECT * FROM single_video ORDER BY id DESC ", nativeQuery = true)
    public List<singleVideo> getAllVideo();
}
