package com.elijahukeme.onlinelearningappbackend.controller.user;


import com.elijahukeme.onlinelearningappbackend.dto.image.ImageDto;
import com.elijahukeme.onlinelearningappbackend.dto.user.UserDto;
import com.elijahukeme.onlinelearningappbackend.exception.main.DataAlreadyExistException;
import com.elijahukeme.onlinelearningappbackend.exception.main.DataNotFoundException;
import com.elijahukeme.onlinelearningappbackend.model.image.ImageModel;
import com.elijahukeme.onlinelearningappbackend.response.api.ApiResponse;
import com.elijahukeme.onlinelearningappbackend.service.main.image.ImageService;
import com.elijahukeme.onlinelearningappbackend.service.main.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;

    @PostMapping("/user/validate")
    public ApiResponse saveUserInfo(@RequestBody UserDto userDto) throws DataAlreadyExistException, DataNotFoundException {
        return userService.saveUserInfo(userDto);
    }

    @PostMapping("profileImage/upload")
    public ResponseEntity<ImageDto> saveProfileImage(@RequestParam("file") MultipartFile file) throws Exception {
        ImageModel imageModel = imageService.saveProfileImage(file);
        String downloadUrl = "upload/"+imageModel.getId();
        return new ResponseEntity<>(new ImageDto(imageModel.getFileName(),downloadUrl,file.getContentType(), file.getSize()), HttpStatus.CREATED);
    }

    @GetMapping("/upload/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable("imageId") String imageId) throws Exception {
        ImageModel imageModel = imageService.getImage(imageId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageModel.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"imageModel; filename=\""+imageModel.getFileName()
                        +"\"")
                .body(new ByteArrayResource(imageModel.getData()));

    }

}
