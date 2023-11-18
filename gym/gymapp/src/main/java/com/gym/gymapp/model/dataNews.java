package com.gym.gymapp.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "data_news")
public class dataNews {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "title", columnDefinition = "TEXT")
    private String Title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String Content;

    @Column(name = "newsdate")
    private Date NewsDate;

    @Column(name = "idimagedata")
    private Long IdImageData;

    // @ManyToOne
    // @JoinColumn(name = "idimagedata", insertable = false, updatable = false)
    // public NewsDTO newsDTO;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getNewsDate() {
        return NewsDate;
    }

    public void setNewsDate(Date newsDate) {
        NewsDate = newsDate;
    }

    public Long getIdImageData() {
        return IdImageData;
    }

    public void setIdImageData(Long idImageData) {
        IdImageData = idImageData;
    }

    // public NewsDTO getNewsDTO() {
    //     return newsDTO;
    // }

    // public void setNewsDTO(NewsDTO newsDTO) {
    //     this.newsDTO = newsDTO;
    // }

    

}
