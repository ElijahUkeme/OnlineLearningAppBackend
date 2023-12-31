package com.elijahukeme.onlinelearningappbackend.dto.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    private String fileName;
    private String downloadUrl;
    private String fileType;
    private long fileSize;

}