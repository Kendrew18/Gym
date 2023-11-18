package com.gym.gymapp.repositorys;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gym.gymapp.model.storeGym;

public interface storeGymRepositorys extends JpaRepository<storeGym, Long>{

    @Query(value = "SELECT * FROM data_store WHERE LOWER(deskripsi) LIKE LOWER(:text) OR LOWER(title) LIKE LOWER(:text)", nativeQuery = true)
    public List<storeGym> searchdataStore(@Param("text") String text);

    @Query(value = "SELECT id, id_foto, harga FROM data_store ORDER BY date DESC LIMIT 7", nativeQuery = true)
    public List<Map<String,Object>> get7data();

    @Query(value = "SELECT id, title, id_foto, harga FROM data_store", nativeQuery = true)
    public List<Map<String,Object>> getjudul();
}
