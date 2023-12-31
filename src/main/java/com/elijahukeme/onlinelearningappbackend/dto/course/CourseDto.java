package com.elijahukeme.onlinelearningappbackend.dto.course;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private String courseName;
    private String description;
    private String video;
    private double price;
}
