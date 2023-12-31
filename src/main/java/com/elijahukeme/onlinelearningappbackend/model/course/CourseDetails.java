package com.elijahukeme.onlinelearningappbackend.model.course;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetails {
    private int code;
    private String msg;
    private List<CourseModel> data;
}
