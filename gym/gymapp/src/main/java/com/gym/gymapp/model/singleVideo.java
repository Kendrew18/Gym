package com.gym.gymapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "singleVideo")
public class singleVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "video_name", columnDefinition = "TEXT")
    private String video_name;

    @Column(name = "video_desc", columnDefinition = "TEXT")
    private String video_desc;

    @Column(name = "video_difficulty")
    private String video_difficulty;

    @Column(name = "video_length")
    private double video_length;

    @Column(name = "video_path", columnDefinition = "TEXT")
    private String video_path;

    public long getVideoID() {
        return id;
    }

    public void setVideoID(long id) {
        this.id = id;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_desc() {
        return video_desc;
    }

    public void setVideo_desc(String video_desc) {
        this.video_desc = video_desc;
    }

    public double getVideo_length() {
        return video_length;
    }

    public void setVideo_length(double video_length) {
        this.video_length = video_length;
    }

    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public String getVideo_difficulty() {
        return video_difficulty;
    }

    public void setVideo_difficulty(String video_difficulty) {
        this.video_difficulty = video_difficulty;
    }
    
}
