package com.elijahukeme.onlinelearningappbackend.controller.course;

import com.elijahukeme.onlinelearningappbackend.dto.course.CourseDto;
import com.elijahukeme.onlinelearningappbackend.dto.course.CourseUpdateDto;
import com.elijahukeme.onlinelearningappbackend.exception.main.DataNotFoundException;
import com.elijahukeme.onlinelearningappbackend.model.course.CourseDetails;
import com.elijahukeme.onlinelearningappbackend.model.course.CourseModel;
import com.elijahukeme.onlinelearningappbackend.model.image.ImageModel;
import com.elijahukeme.onlinelearningappbackend.response.course.CourseResponse;
import com.elijahukeme.onlinelearningappbackend.service.main.course.CourseService;
import com.elijahukeme.onlinelearningappbackend.service.main.image.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.logging.Logger;

@Slf4j
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ImageService imageService;

    @PostMapping("/course/create")
    public CourseResponse createCourse(@RequestParam("file")MultipartFile courseThumbNail, @RequestPart("course")CourseDto courseDto) throws Exception {
        ImageModel imageModel = imageService.saveProfileImage(courseThumbNail);
        String downloadUrl = "download/"+imageModel.getId();
        return courseService.createCourse(courseDto,downloadUrl);
    }

    @PutMapping("/course/update/with/video")
    public CourseModel updateCourseData(@RequestParam("file")MultipartFile file,@RequestParam("courseId") Integer courseId) throws Exception {
        ImageModel imageModel = imageService.saveProfileImage(file);
        String videoUrl = "video/"+imageModel.getId();
        CourseUpdateDto courseUpdateDto = new CourseUpdateDto();
        courseUpdateDto.setCourseId(courseId);
        courseUpdateDto.setFileType(imageModel.getFileType());
        courseUpdateDto.setFileSize(file.getSize());
        courseUpdateDto.setVideoPath(videoUrl);
        return courseService.updateCourseWithVideo(file,courseUpdateDto);
    }

    @PutMapping("/course/update")
    public CourseModel updateCourseDetails(@RequestBody CourseDto courseDto,@PathVariable("courseId") Integer courseId) throws DataNotFoundException {
        return courseService.updateCourseInfo(courseDto,courseId);
    }
    @GetMapping("/video/{videoId}")
    public ResponseEntity<Resource> downloadVideoPath(@PathVariable("videoId")String videoId){
        ImageModel imageModel = imageService.getImage(videoId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageModel.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"imageModel; filename=\""+imageModel.getFileName()
                +"\"")
                .body(new ByteArrayResource(imageModel.getData()));
    }

    @GetMapping("/download/{imageId}")
    public ResponseEntity<Resource> downloadCourseThumbnail(@PathVariable("imageId") String imageId) throws Exception {
        ImageModel imageModel = imageService.getImage(imageId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageModel.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"imageModel; filename=\""+imageModel.getFileName()
                        +"\"")
                .body(new ByteArrayResource(imageModel.getData()));

    }

    @GetMapping("/courseList")
    public CourseDetails allAvailableCourses(){
        return courseService.getAllCourseDetails();
    }
}
