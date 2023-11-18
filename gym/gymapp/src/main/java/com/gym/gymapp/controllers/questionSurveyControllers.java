package com.gym.gymapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.gymapp.model.questionSurvey;
import com.gym.gymapp.repositorys.questionSurveyRepositorys;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class questionSurveyControllers {
    @Autowired
    questionSurveyRepositorys qUserGymRepositorys;

    @PostMapping("/edit_survey")
    public ResponseEntity<?> editSurvey(@RequestBody questionSurvey entity) {
        
        Map<String, Object> responseBody = new HashMap<>();
        try {
            if (this.qUserGymRepositorys.cekIdQuestion(entity.getUser_ID()) == 1) {


                this.qUserGymRepositorys.updateUserQuestion(
                    entity.getUser_ID(),
                    entity.getQuestion_1(),
                    entity.getQuestion_2(),
                    entity.getQuestion_3(),
                    entity.getQuestion_4()
                );
                
                responseBody.put("success", true);
                responseBody.put("message", "User Survey Answer Edited!");
                responseBody.put("userID", entity.getUser_ID());
                responseBody.put("question_1", entity.getQuestion_1());
                responseBody.put("question_2", entity.getQuestion_2());
                responseBody.put("question_3", entity.getQuestion_3());
                responseBody.put("question_4", entity.getQuestion_4());

                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
            } else {
                responseBody.put("success", false);
                responseBody.put("message", "User ID not found!");

                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getQuestion/{id}")
    public ResponseEntity<?> getQuestionAnswer(@PathVariable("id") Long id) {
        try {
            questionSurvey questionUser = this.qUserGymRepositorys.getUserSurvey(id);
            return ResponseEntity.status(HttpStatus.OK).body(questionUser);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }

}
