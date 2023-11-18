package com.gym.gymapp.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gym.gymapp.model.singleVideo;
import com.gym.gymapp.repositorys.singleVideoRepositorys;

import jakarta.annotation.PostConstruct;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin("*")
@Component
public class singleVideoDUController {

    FFmpeg ffmpeg;

    @PostConstruct
    private void init() {
        try {
            this.ffmpeg = new FFmpeg();
        } catch (IOException e) {
            // Handle the exception according to your needs
            e.printStackTrace();
        }
    }

    @Autowired
    private singleVideoRepositorys singleVideoRepositorys;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("videoName") String videoName,
                                                   @RequestParam("videoDescription") String videoDescription,
                                                   @RequestParam("videoDifficulty") String videoDifficulty,
                                                   @RequestParam("videoLength") long videoLength) throws IOException {

        Map<String, Object> responseBody = new HashMap<>();

        String videoPath = saveFile(file, videoName);

        if (videoPath != null) {

            if (!file.getContentType().equals("video/mp4") || file.isEmpty()) {
                responseBody.put("success", false);
                responseBody.put("message", "Please upload MP4 file!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
            }

            double durationInSeconds = getVideoDuration(videoPath);

            singleVideo video = new singleVideo();
            video.setVideo_name(videoName);
            video.setVideo_desc(videoDescription);
            video.setVideo_difficulty(videoDifficulty);
            video.setVideo_length(durationInSeconds);
            video.setVideo_path(videoPath);

            singleVideoRepositorys.save(video);
            
            responseBody.put("success", true);
            responseBody.put("message", "Video Uploaded Successfully!!");
            responseBody.put("video_id", this.singleVideoRepositorys.getlastId());
            responseBody.put("video_name", videoName.replaceAll(" ", "_"));
            responseBody.put("video_desc", videoDescription);
            responseBody.put("video_difficulty", videoDifficulty);
            responseBody.put("video_length", durationInSeconds);

            return ResponseEntity.status(HttpStatus.OK).body(responseBody);

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }

    }

    private String saveFile(MultipartFile file, String videoName) {
        String uploadPath = "/home/project/home/aplikasi/gym/video";
        try {
            String videoSaved = videoName.replaceAll(" ", "_") + ".mp4";
            Files.copy(file.getInputStream(), Paths.get(uploadPath, videoSaved), StandardCopyOption.REPLACE_EXISTING);
            return uploadPath + "/" + videoSaved + ".mp4";
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private double getVideoDuration(String videoPath) throws IOException{
        try {
            FFprobe ffprobe;
            FFmpegProbeResult probeResult;

            ffprobe = new FFprobe("/usr/bin/ffprobe");
            probeResult = ffprobe.probe(videoPath);
            FFmpegFormat format = probeResult.getFormat();
            return format.duration;

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        singleVideo singleVideo = singleVideoRepositorys.findById(id)
            .orElseThrow();

        Resource resource = loadFileAsResource(singleVideo.getVideo_path());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private Resource loadFileAsResource(String filePath) {
        
        try {
            return new FileSystemResource(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllVideo() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.singleVideoRepositorys.getAllVideo());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kosongggg");
        }
    }
}
