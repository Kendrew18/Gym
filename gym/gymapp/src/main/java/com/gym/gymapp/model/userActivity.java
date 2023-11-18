package com.gym.gymapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "useractivity")
public class userActivity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "userid")
    private long userid;

    @Column(name = "time_watch")
    private long time_watch;

    @Column(name = "total_videos")
    private long total_videos;

    @Column(name = "total_training")
    private long total_training;

    public Long getId() {
        return Id;
    }

    public long getUserid() {
        return userid;
    }

    public long getTime_watch() {
        return time_watch;
    }

    public long getTotal_videos() {
        return total_videos;
    }

    public long getTotal_training() {
        return total_training;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public void setTime_watch(long time_watch) {
        this.time_watch = time_watch;
    }

    public void setTotal_videos(long total_videos) {
        this.total_videos = total_videos;
    }

    public void setTotal_training(long total_training) {
        this.total_training = total_training;
    }

    public userActivity orElse(Object object) {
        return null;
    }

}
