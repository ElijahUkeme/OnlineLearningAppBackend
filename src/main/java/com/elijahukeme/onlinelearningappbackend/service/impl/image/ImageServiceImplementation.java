package com.elijahukeme.onlinelearningappbackend.service.impl.image;


import com.elijahukeme.onlinelearningappbackend.model.image.ImageModel;
import com.elijahukeme.onlinelearningappbackend.repository.image.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.elijahukeme.onlinelearningappbackend.service.main.image.ImageService;

@Service
public class ImageServiceImplementation implements ImageService {

    private ImageRepository imageRepository;

    public ImageServiceImplementation(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageModel saveProfileImage(MultipartFile file) throws Exception {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            if (fileName.contains("..")){
                throw new Exception ("File Name Contains invalid character");
            }
            ImageModel imageModel = new ImageModel(file.getContentType(),fileName,file.getBytes());
            return imageRepository.saveAndFlush(imageModel);
        }

    @Override
    public ImageModel getImage(String imageId) {
        return imageRepository.findById(imageId).get();
    }
}
