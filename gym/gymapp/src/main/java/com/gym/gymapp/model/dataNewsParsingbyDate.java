package com.gym.gymapp.model;

import java.util.Date;
import java.util.List;

public class dataNewsParsingbyDate {
    private Date tanggal;
    private List<dataNews> news;
    
    public Date getTanggal() {
        return tanggal;
    }
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    public List<dataNews> getNews() {
        return news;
    }
    public void setNews(List<dataNews> news) {
        this.news = news;
    }   

    
}
