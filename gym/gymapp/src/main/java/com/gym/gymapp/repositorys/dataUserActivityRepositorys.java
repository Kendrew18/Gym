package com.gym.gymapp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gym.gymapp.model.userActivity;

import jakarta.transaction.Transactional;

public interface dataUserActivityRepositorys extends JpaRepository <userActivity, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO useractivity (userid, time_watch, total_videos, total_training) " + 
    "VALUES (:userid, :time_watch, :total_videos, :total_training)", nativeQuery = true)
    void addUserActivity(
        @Param("userid") long userid, 
        @Param("time_watch") long time_watch, 
        @Param("total_videos") long total_videos, 
        @Param("total_training") long total_training
    );
    
    @Query(value = "SELECT * FROM useractivity WHERE userid = :userid", nativeQuery = true)
    public userActivity getUserActivity(
        @Param("userid") long userid
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE useractivity SET time_watch = :time_watch, total_videos = :total_videos, total_training = :total_training WHERE userid = :userid", nativeQuery = true)
    void updateUserActivity(
        @Param("userid") long userid, 
        @Param("time_watch") long time_watch, 
        @Param("total_videos") long total_videos, 
        @Param("total_training") long total_training
    );

    @Query(value = "SELECT CASE WHEN EXISTS (" +
    "SELECT * FROM useractivity " + 
    "WHERE id = :id ) THEN TRUE ELSE FALSE END AS result", nativeQuery = true )
    public Integer cekIdAct(@Param("id") long id);
}
