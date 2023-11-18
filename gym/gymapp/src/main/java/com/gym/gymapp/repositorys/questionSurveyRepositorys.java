package com.gym.gymapp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gym.gymapp.model.questionSurvey;

import jakarta.transaction.Transactional;

public interface questionSurveyRepositorys extends JpaRepository <questionSurvey, Long>{

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO question_survey (user_id, question_1, question_2, question_3, question_4) " + 
    "VALUES (:userid, :question_1, :question_2, :question_3, :question_4)", nativeQuery = true)
    void addUserSurveyQuestion(
        @Param("userid") long userid,
        @Param("question_1") String question_1, 
        @Param("question_2") String question_2, 
        @Param("question_3") String question_3,
        @Param("question_4") String question_4
    );

    @Query(value = "SELECT * FROM question_survey WHERE user_id = :userid", nativeQuery = true)
    public questionSurvey getUserSurvey(
        @Param("userid") long userid
    );

    @Query(value = "SELECT CASE WHEN EXISTS (" +
    "SELECT * FROM question_survey " + 
    "WHERE user_ID = :user_id ) THEN TRUE ELSE FALSE END AS result", nativeQuery = true )
    public Integer cekIdQuestion(@Param("user_id") long user_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE question_survey SET question_1 = :question_1, question_2 = :question_2, question_3 = :question_3, question_4 = :question_4 WHERE user_id = :userid", nativeQuery = true)
    void updateUserQuestion(
        @Param("userid") long userid, 
        @Param("question_1") String question_1, 
        @Param("question_2") String question_2, 
        @Param("question_3") String question_3,
        @Param("question_4") String question_4
    );
}
