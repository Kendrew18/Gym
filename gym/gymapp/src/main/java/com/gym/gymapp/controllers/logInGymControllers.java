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
import com.gym.gymapp.model.userLogin;
import com.gym.gymapp.repositorys.userGymRepositorys;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class logInGymControllers {
    @Autowired
    userGymRepositorys userGymRepositorys;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody userLogin entity) {
        // TODO: process POST request
        Map<String, Object> responseBody = new HashMap<>();

        try {
            if (this.userGymRepositorys.cekLogin(entity.getEmail(), entity.getPassword()) == 1) {

                userGym data = this.userGymRepositorys.getLogin(entity.getEmail(), entity.getPassword());
                responseBody.put("success", true);
                responseBody.put("message", "Login Success");
                responseBody.put("userID", data.getId());
                responseBody.put("fullname", data.getFullname());

                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
            } else {
                responseBody.put("success", false);
                responseBody.put("message", "cant login");
                responseBody.put("userID", "");

                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
