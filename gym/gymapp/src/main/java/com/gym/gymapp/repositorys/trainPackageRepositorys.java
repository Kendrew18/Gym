package com.gym.gymapp.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gym.gymapp.model.trainPackage;

import jakarta.transaction.Transactional;

public interface trainPackageRepositorys extends JpaRepository<trainPackage, Long> {
    
    @Query(value = "SELECT LAST_INSERT_ID() AS new_id; ", nativeQuery = true)
    public Long getlastId();

    @Query(value = "SELECT * FROM package_training ORDER BY id DESC ", nativeQuery = true)
    public List<trainPackage> getAllPackage();

    @Query(value = "SELECT * FROM package_training WHERE id = :id", nativeQuery = true)
    void getSpecificPackages(
        @Param("id") long userid
    );

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO package_training (package_name, package_desc, package_difficulty, package_vid_list) " + 
    "VALUES (:package_name, :package_desc, :package_difficulty, :package_vid_list)", nativeQuery = true)
    void addTrainingPackage(
        @Param("package_name") String package_name,
        @Param("package_desc") String package_desc, 
        @Param("package_difficulty") String package_difficulty, 
        @Param("package_vid_list") String package_vid_list
    );
}
