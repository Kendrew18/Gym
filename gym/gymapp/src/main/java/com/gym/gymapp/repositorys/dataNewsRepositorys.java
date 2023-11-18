package com.gym.gymapp.repositorys;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gym.gymapp.model.dataNews;

public interface dataNewsRepositorys extends JpaRepository<dataNews, Long> {
    
    @Query(value = "SELECT * FROM data_news ORDER BY newsdate DESC LIMIT 2", nativeQuery = true)
    public List<dataNews> get2data();

    @Query(value = "SELECT newsdate FROM data_news GROUP BY newsdate", nativeQuery = true)
    public List<Date> getdateNews();

    
    @Query(value = "SELECT * FROM data_news ORDER BY id DESC", nativeQuery = true)
    public List<dataNews> getdataNewsAll();

    @Query(value = "SELECT * FROM data_news WHERE LOWER(content) LIKE LOWER(:text) OR LOWER(title) LIKE LOWER(:text)", nativeQuery = true)
    public List<dataNews> searchdataNews(@Param("text") String text);
}
