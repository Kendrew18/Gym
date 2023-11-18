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

import com.gym.gymapp.model.userActivity;
import com.gym.gymapp.model.userGym;
import com.gym.gymapp.repositorys.dataUserActivityRepositorys;
import com.gym.gymapp.repositorys.userGymRepositorys;

// ------------------------ < HANDLE GET/UPDATE USER ACTIVITY & EDIT USER INFO > ------------------------//

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class userActivityControllers {
    @Autowired
    userGymRepositorys userGymRepositorys;

    @Autowired
    dataUserActivityRepositorys dataUserActivityRepositorys;

    @PostMapping("/edit_profile")
    public ResponseEntity<?> edit_profile(@RequestBody userGym entity) {
        
        Map<String, Object> responseBody = new HashMap<>();
        try {
            if (this.userGymRepositorys.cekId(entity.getId()) == 1) {
                // System.out.println(entity.getEmail());
                // System.out.println(entity.getPassword());
                // System.out.println(entity.getNo_Handphone());
                // System.out.println(entity.getAlamat());
                // System.out.println(entity.getStatus());
                // System.out.println(entity.getFullname());


                this.userGymRepositorys.update_profile(
                    entity.getId(),
                    entity.getEmail(),
                    entity.getPassword(),
                    entity.getNo_Handphone(),
                    entity.getAlamat(),
                    entity.getStatus(),
                    entity.getFullname()
                );
                
                responseBody.put("success", true);
                responseBody.put("message", "User Profile Editted!");
                responseBody.put("userID", entity.getId().toString());
                responseBody.put("email", entity.getEmail());
                responseBody.put("no_handphone", entity.getNoHandphone());
                responseBody.put("alamat", entity.getAlamat());
                responseBody.put("status", entity.getStatus());
                responseBody.put("fullname", entity.getFullname());

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

    @GetMapping("/getProfile/{id}")
    public ResponseEntity<?> getprofile(@PathVariable("id") Long id) {
        try {
            userGym dataUser = this.userGymRepositorys.findById(id).orElse(null);
            // dataUser.setPassword("");
            return ResponseEntity.status(HttpStatus.OK).body(dataUser);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }


    @GetMapping("/getActivity/{id}")
    public ResponseEntity<?> getActivity(@PathVariable("id") long id) {
        try {
            userActivity userAct = this.dataUserActivityRepositorys.getUserActivity(id);
            return ResponseEntity.status(HttpStatus.OK).body(userAct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }

    @PostMapping("/edit_activity")
    public ResponseEntity<?> edit_act(@RequestBody userActivity entity) {
        
        Map<String, Object> responseBody = new HashMap<>();
        try {
            if (this.dataUserActivityRepositorys.cekIdAct(entity.getId()) == 1) {

                this.dataUserActivityRepositorys.updateUserActivity(
                    entity.getId(),
                    entity.getTime_watch(),
                    entity.getTotal_videos(),
                    entity.getTotal_training()
                );
                
                responseBody.put("success", true);
                responseBody.put("message", "User Activity Editted!");
                responseBody.put("userID", entity.getId().toString());
                responseBody.put("time_watch", entity.getTime_watch());
                responseBody.put("total_video", entity.getTotal_videos());
                responseBody.put("total_training", entity.getTotal_training());

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
}
