package com.elijahukeme.onlinelearningappbackend.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseUpdateDto {

    private int courseId;
    private String videoPath;
    private String fileType;
    private long fileSize;
}
