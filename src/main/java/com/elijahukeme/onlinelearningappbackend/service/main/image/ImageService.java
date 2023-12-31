package com.elijahukeme.onlinelearningappbackend.service.main.image;

import com.elijahukeme.onlinelearningappbackend.model.image.ImageModel;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageModel saveProfileImage(MultipartFile file) throws Exception;
    ImageModel getImage(String imageId);
}
