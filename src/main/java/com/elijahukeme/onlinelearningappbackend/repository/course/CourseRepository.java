package com.elijahukeme.onlinelearningappbackend.repository.course;

import com.elijahukeme.onlinelearningappbackend.model.course.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<CourseModel,Integer> {
}
