package com.gym.gymapp.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gym.gymapp.model.NewsDTO;
import com.gym.gymapp.repositorys.dataImageNewsRepositorys;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class dataNewsDTOControllers {

    @Autowired
    dataImageNewsRepositorys dataImageNewsRepositorys;

    @PostMapping("/uploadnews")
    public ResponseEntity<?> UploadNews(@RequestParam("file") MultipartFile file) {

        Map<String, Object> responseBody = new HashMap<>();
        NewsDTO news = new NewsDTO();
        news.setName(file.getOriginalFilename());
        try {
            news.setImageData(file.getBytes());
            news.setType(file.getContentType());
            this.dataImageNewsRepositorys.save(news);
            responseBody.put("id", this.dataImageNewsRepositorys.getlastId());
            responseBody.put("massage", "News berhasil disimpan");
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseBody.put("id", null);
            responseBody.put("massage", "News eror disimpan");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("News eror disimpan");
        }
    }

    @GetMapping("/downloadnews/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable("id") Long fileId) throws IOException {
        NewsDTO news = this.dataImageNewsRepositorys.findById(fileId).orElse(null);
        if (news != null && news.getImageData() != null) {
            try {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .body(news.getImageData());
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no conttent");
            }
        } else {
            // Handle the case where the file does not exist or is empty
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }

}
