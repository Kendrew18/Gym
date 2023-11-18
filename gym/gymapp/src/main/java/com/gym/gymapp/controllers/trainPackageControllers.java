package com.gym.gymapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.gymapp.model.trainPackage;
import com.gym.gymapp.repositorys.trainPackageRepositorys;

@RestController
@RequestMapping("/api/packages")
@CrossOrigin("*")
@Component
public class trainPackageControllers {
    

    @Autowired
    trainPackageRepositorys trainPackageRepositorys;

    @PostMapping("/train_package/add")
    public ResponseEntity<?> addTrainingPackage(@RequestBody trainPackage entity) {
        
        Map<String, Object> responseBody = new HashMap<>();    
        try { 
            
            System.out.println(entity.getPackage_name());
            System.out.println(entity.getPackage_desc());
            System.out.println(entity.getPackage_difficulty());
            System.out.println(entity.getPackageVidList());
            
            this.trainPackageRepositorys.addTrainingPackage(
                entity.getPackage_name(),
                entity.getPackage_desc(), 
                entity.getPackage_difficulty(), 
                entity.getPackageVidList().toString()
            );

            return ResponseEntity.status(HttpStatus.OK).body(responseBody);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
