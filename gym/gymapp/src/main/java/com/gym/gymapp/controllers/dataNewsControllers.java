package com.gym.gymapp.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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
import com.gym.gymapp.model.dataNewsParsingbyDate;
import com.gym.gymapp.repositorys.dataNewsRepositorys;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class dataNewsControllers {

    @Autowired
    dataNewsRepositorys dataNewsRepositorys;

    @PostMapping("/savenews")
    public ResponseEntity<?> saveNews(@RequestBody dataNews data) {

        Map<String, Object> responseBody = new HashMap<>();
        Date date = new Date();
        try {
            System.out.println(data.getIdImageData());
            dataNews data2 = new dataNews();
            data2.setContent(data.getContent());
            data2.setNewsDate(date);
            data2.setTitle(data.getTitle());
            data2.setIdImageData(data.getIdImageData());
            this.dataNewsRepositorys.save(data2);
            responseBody.put("massage", "News berhasil disimpan");
            responseBody.put("massage2", data);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            // TODO: handle exception
            responseBody.put("massage", "News tidak berhasil disimpan");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseBody);
        }
    }

    @GetMapping("/getData")
    public ResponseEntity<?> getAlldata() {
        List<dataNewsParsingbyDate> responbody = new ArrayList();
        try {
            List<Date> datenews = this.dataNewsRepositorys.getdateNews();
            List<dataNews> data1 = this.dataNewsRepositorys.getdataNewsAll();

            for (int i = 0; i < datenews.size(); i++) {
                dataNewsParsingbyDate data = new dataNewsParsingbyDate();
                data.setTanggal(datenews.get(i));
                List<dataNews> newsList = new ArrayList<>();

                for(int j=0; j < data1.size(); j++){
                    if(datenews.get(i).equals(data1.get(j).getNewsDate())){
                        
                        newsList.add(data1.get(j));
                    }
                }
                data.setNews(newsList);
                responbody.add(data);
            }

            return ResponseEntity.status(HttpStatus.OK).body(responbody);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }

    @GetMapping("/getData/{id}")
    public ResponseEntity<?> getdatabyid(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.dataNewsRepositorys.findById(id).orElse(null));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }

    @GetMapping("/searchNews/{text}")
    public ResponseEntity<?> searchNews(@PathVariable("text") String text) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.dataNewsRepositorys.searchdataNews("%"+text+"%"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }
    @GetMapping("/searchNews/")
    public ResponseEntity<?> searchNews2() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.dataNewsRepositorys.findAll());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }

    @GetMapping("/get2DataNews")
    public ResponseEntity<?> get2dataNews() {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(this.dataNewsRepositorys.get2data());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }

}
