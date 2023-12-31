package com.elijahukeme.onlinelearningappbackend.service.main.course;

import com.elijahukeme.onlinelearningappbackend.dto.course.CourseDto;
import com.elijahukeme.onlinelearningappbackend.dto.course.CourseUpdateDto;
import com.elijahukeme.onlinelearningappbackend.exception.main.DataNotFoundException;
import com.elijahukeme.onlinelearningappbackend.model.course.CourseDetails;
import com.elijahukeme.onlinelearningappbackend.model.course.CourseModel;
import com.elijahukeme.onlinelearningappbackend.response.course.CourseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    public CourseResponse createCourse(CourseDto courseDto, String courseThumbnail);
    public CourseModel updateCourseWithVideo(MultipartFile multipartFile, CourseUpdateDto courseUpdateDto) throws DataNotFoundException;
    public List<CourseModel> getAllCourses();
    public CourseDetails getAllCourseDetails();
    public CourseModel updateCourseInfo(CourseDto courseDto,Integer courseId) throws DataNotFoundException;
}
