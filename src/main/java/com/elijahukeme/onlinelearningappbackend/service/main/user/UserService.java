package com.elijahukeme.onlinelearningappbackend.service.main.user;

import com.elijahukeme.onlinelearningappbackend.dto.user.UserDto;
import com.elijahukeme.onlinelearningappbackend.exception.main.DataAlreadyExistException;
import com.elijahukeme.onlinelearningappbackend.exception.main.DataNotFoundException;
import com.elijahukeme.onlinelearningappbackend.response.api.ApiResponse;

public interface UserService {

    public ApiResponse saveUserInfo(UserDto userDto) throws DataAlreadyExistException, DataNotFoundException;
}
