package com.elijahukeme.onlinelearningappbackend.service.impl.user;


import com.elijahukeme.onlinelearningappbackend.dto.user.UserDto;
import com.elijahukeme.onlinelearningappbackend.exception.main.DataAlreadyExistException;
import com.elijahukeme.onlinelearningappbackend.exception.main.DataNotFoundException;
import com.elijahukeme.onlinelearningappbackend.model.user.UserModel;
import com.elijahukeme.onlinelearningappbackend.response.api.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import com.elijahukeme.onlinelearningappbackend.service.main.user.UserService;
import com.elijahukeme.onlinelearningappbackend.repository.user.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ApiResponse saveUserInfo(UserDto userDto) throws DataAlreadyExistException, DataNotFoundException {
        return authenticateUser(userDto);
    }

    private ApiResponse authenticateUser(UserDto userDto) throws DataAlreadyExistException, DataNotFoundException {
        UserModel userModelFromToken = userRepository.findByAccessToken(userDto.getAccessToken());
        UserModel userModelFromEmail = userRepository.findByEmail(userDto.getEmail());
        if (Objects.nonNull(userModelFromToken) && Objects.nonNull(userModelFromEmail)) {
            if (userModelFromToken == userModelFromEmail) {
                return new ApiResponse("Login Successfully", userModelFromToken, HttpStatus.OK.value());
            } else if (userModelFromToken !=userModelFromEmail){
                throw new DataNotFoundException("Email address and access token from google not matched");
            }
        }
        return new ApiResponse("User Registration Successfully", registerUser(userDto), HttpStatus.OK.value());
    }

    private UserModel registerUser(UserDto userDto) throws DataAlreadyExistException {
        UserModel userModelFromToken = userRepository.findByAccessToken(userDto.getAccessToken());
        UserModel userModelFromEmail = userRepository.findByEmail(userDto.getEmail());
        UserModel userModel;
        if (Objects.nonNull(userModelFromEmail) && Objects.isNull(userModelFromToken)) {
            throw new DataAlreadyExistException("Email Address Already Taken");
        } else {
            userModel = new UserModel();
            userModel.setType(userDto.getType());
            userModel.setAccessToken(userDto.getAccessToken());
            userModel.setName(userDto.getName());
            userModel.setProfileImage(userDto.getProfileImage());
            userModel.setEmail(userDto.getEmail());
            userRepository.save(userModel);
        }
        return userModel;
    }
    }


