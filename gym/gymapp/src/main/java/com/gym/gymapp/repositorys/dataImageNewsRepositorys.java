package com.gym.gymapp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gym.gymapp.model.NewsDTO;

public interface dataImageNewsRepositorys extends JpaRepository<NewsDTO, Long> {
    
    @Query(value = "SELECT LAST_INSERT_ID() AS new_id; ", nativeQuery = true)
    public Long getlastId();
}
