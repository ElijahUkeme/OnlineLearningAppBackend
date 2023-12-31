package com.elijahukeme.onlinelearningappbackend.service.impl.course;

import com.elijahukeme.onlinelearningappbackend.dto.course.CourseDto;
import com.elijahukeme.onlinelearningappbackend.dto.course.CourseUpdateDto;
import com.elijahukeme.onlinelearningappbackend.exception.main.DataNotFoundException;
import com.elijahukeme.onlinelearningappbackend.model.course.CourseDetails;
import com.elijahukeme.onlinelearningappbackend.model.course.CourseModel;
import com.elijahukeme.onlinelearningappbackend.repository.course.CourseRepository;
import com.elijahukeme.onlinelearningappbackend.response.course.CourseResponse;
import com.elijahukeme.onlinelearningappbackend.service.main.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseServiceImplementation implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Override
    public CourseResponse createCourse(CourseDto courseDto, String courseThumbnail) {
        CourseModel courseModel = new CourseModel();
        courseModel.setCourseName(courseDto.getCourseName());
        courseModel.setDescription(courseDto.getDescription());
        courseModel.setPrice(courseDto.getPrice());
        courseModel.setVideo(courseDto.getVideo());
        courseModel.setThumbnail(courseThumbnail);
        courseRepository.save(courseModel);
        return new CourseResponse("Course created Successfully",true, HttpStatus.OK.value());
    }

    @Override
    public CourseModel updateCourseWithVideo(MultipartFile multipartFile, CourseUpdateDto courseUpdateDto) throws DataNotFoundException {
        Optional <CourseModel> courseModel = courseRepository.findById(courseUpdateDto.getCourseId());
        if (Objects.isNull(courseModel)){
            throw new DataNotFoundException("There is no course with the provided id");
        }
        courseModel.get().setVideo(courseUpdateDto.getVideoPath());
        courseModel.get().setFileSize(courseUpdateDto.getFileSize());
        courseModel.get().setFileType(courseUpdateDto.getFileType());
        return courseRepository.save(courseModel.get());
    }

    @Override
    public List<CourseModel> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public CourseDetails getAllCourseDetails() {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setCode(HttpStatus.OK.value());
        courseDetails.setMsg("All Available Courses");
        courseDetails.setData(getAllCourses());
        return courseDetails;
    }

    @Override
    public CourseModel updateCourseInfo(CourseDto courseDto, Integer courseId) throws DataNotFoundException {
        Optional<CourseModel> courseModel = courseRepository.findById(courseId);
        if (Objects.isNull(courseModel)){
            throw new DataNotFoundException("Course Id not found");
        }
        if (Objects.nonNull(courseDto.getCourseName())&& !"".equalsIgnoreCase(courseDto.getCourseName())){
            courseModel.get().setCourseName(courseDto.getCourseName());
        }
        if (Objects.nonNull(courseDto.getDescription())&& !"".equalsIgnoreCase(courseDto.getDescription())){
            courseModel.get().setDescription(courseDto.getDescription());
        }
        if (Objects.nonNull(courseDto.getVideo())&& !"".equalsIgnoreCase(courseDto.getVideo())){
            courseModel.get().setVideo(courseDto.getVideo());
        }
        if (Objects.nonNull(courseDto.getPrice())&& courseDto.getPrice()!=0){
            courseModel.get().setPrice(courseDto.getPrice());
        }
        return courseModel.get();
    }
}
