package com.gym.gymapp.controllers;

import java.util.Date;
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

import com.gym.gymapp.model.dataNews;
import com.gym.gymapp.model.storeGym;
import com.gym.gymapp.repositorys.storeGymRepositorys;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class storeGymControllers {

    @Autowired
    storeGymRepositorys storeGymRepositorys;

    @PostMapping("/saveStore")
    public ResponseEntity<?> saveNews(@RequestBody storeGym data) {

        Map<String, Object> responseBody = new HashMap<>();
        try {
            this.storeGymRepositorys.save(data);
            responseBody.put("massage", "News berhasil disimpan");
            responseBody.put("title", data.getTitle());
            responseBody.put("harga", data.getHarga());
            responseBody.put("data", data);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            // TODO: handle exception
            responseBody.put("massage", "News tidak berhasil disimpan");
            responseBody.put("massage", data);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseBody);
        }
    }

    @GetMapping("/searchStore/{text}")
    public ResponseEntity<?> searchNews(@PathVariable("text") String text) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.storeGymRepositorys.searchdataStore("%" + text + "%"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }
    @GetMapping("/searchStore/")
    public ResponseEntity<?> searchNews2() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.storeGymRepositorys.findAll());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }

    @GetMapping("/getAllStoreData")
    public ResponseEntity<?> alldata() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.storeGymRepositorys.findAll());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }

    @GetMapping("/getAllStoreData2")
    public ResponseEntity<?> alldata2() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.storeGymRepositorys.getjudul());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }

    @GetMapping("/get7DataStore")
    public ResponseEntity<?> get2dataNews() {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(this.storeGymRepositorys.get7data());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }

    @GetMapping("/getDataStore/{id}")
    public ResponseEntity<?> getdatabyid(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.storeGymRepositorys.findById(id).orElse(null));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }

}
