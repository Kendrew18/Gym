package com.gym.gymapp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gym.gymapp.model.userGym;

import jakarta.transaction.Transactional;

public interface userGymRepositorys extends JpaRepository <userGym, Long>{

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO usergym (email,password,no_handphone,alamat,status, fullname) " +
    "values (:email, :password, :no_handphone, :alamat, :status, :fullname)" , nativeQuery = true)
    void SignInUser(@Param("email") String email, @Param("password") String password, @Param("no_handphone") String no_handphone, @Param("alamat") String alamat, @Param("status") String status, @Param("fullname") String fullname);

    @Query(value = "SELECT CASE WHEN EXISTS (" +
    "SELECT * FROM usergym " + 
    "WHERE email = :email ) THEN TRUE ELSE FALSE END AS result", nativeQuery = true )
    public Integer cekSignin(@Param("email") String email);

    @Query(value = "SELECT LAST_INSERT_ID() AS new_id; ", nativeQuery = true)
    public Long getlastId();

    @Query(value = "SELECT CASE WHEN EXISTS (" +
    "SELECT * FROM usergym " + 
    "WHERE email = :email AND password = :password " +
    ") THEN TRUE ELSE FALSE END AS result", nativeQuery = true )
    public Integer cekLogin(@Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT * FROM usergym WHERE email = :email AND password = :password", nativeQuery = true)
    public userGym getLogin(@Param("email") String email, @Param("password") String password);


    // ------------------------------- < UPDATE PROFILE > ------------------------------- //
    @Query(value = "SELECT CASE WHEN EXISTS (" +
    "SELECT * FROM usergym " + 
    "WHERE id = :user_id ) THEN TRUE ELSE FALSE END AS result", nativeQuery = true )
    public Integer cekId(@Param("user_id") long user_id);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE usergym u SET u.email = :email, u.password = :password, u.no_handphone = :no_handphone" + 
    ", u.alamat = :alamat, u.status = :status, u.fullname = :fullname WHERE u.id = :id", nativeQuery = true)
    void update_profile(
        @Param("id") long id,
        @Param("email") String email, 
        @Param("password") String password, 
        @Param("no_handphone") String no_handphone, 
        @Param("alamat") String alamat, 
        @Param("status") String status, 
        @Param("fullname") String fullname
    );
} 
