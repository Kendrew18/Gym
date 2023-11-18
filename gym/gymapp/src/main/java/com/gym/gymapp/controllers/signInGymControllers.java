package com.gym.gymapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.gymapp.model.userGym;
import com.gym.gymapp.repositorys.dataUserActivityRepositorys;
import com.gym.gymapp.repositorys.questionSurveyRepositorys;
import com.gym.gymapp.repositorys.userGymRepositorys;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class signInGymControllers {

    @Autowired
    userGymRepositorys userGymRepositorys;
    
    @Autowired
    dataUserActivityRepositorys dataUserActivityRepositorys;
    
    @Autowired
    questionSurveyRepositorys qSurveyRepositorys;

    @PostMapping("/signin")
    public ResponseEntity<?> sigin(@RequestBody userGym entity) {
        // TODO: process POST request

        System.out.println(entity.getNoHandphone());
        System.out.println(entity.getAlamat());
        System.out.println(entity.getPassword());
        Map<String, Object> responseBody = new HashMap<>();
        try {
            if (this.userGymRepositorys.cekSignin(entity.getEmail()) == 1) {
                
                responseBody.put("success", false);
                responseBody.put("message", "Have same Email");
                responseBody.put("UserID", "");

                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
            } else {

                this.userGymRepositorys.SignInUser(entity.getEmail(), entity.getPassword(), entity.getNoHandphone(),
                        entity.getAlamat(), "Active", entity.getFullname());
                
                // TAMBAHAN BUAT TABLE ACTIVITY
                this.dataUserActivityRepositorys.addUserActivity(
                    this.userGymRepositorys.getlastId(), 
                    0, 
                    0, 
                    0
                );

                // TAMBAHAN BUAT TABLE SURVEY
                this.qSurveyRepositorys.addUserSurveyQuestion(
                    this.userGymRepositorys.getlastId(),
                    null,
                    null,
                    null,
                    null);

                responseBody.put("success", true);
                responseBody.put("message", "UserCreated");
                responseBody.put("userID", this.userGymRepositorys.getlastId().toString());
                responseBody.put("Fullname", entity.getFullname());

                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
