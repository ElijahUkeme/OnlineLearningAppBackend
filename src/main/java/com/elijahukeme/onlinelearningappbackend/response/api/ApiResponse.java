package com.elijahukeme.onlinelearningappbackend.response.api;


import com.elijahukeme.onlinelearningappbackend.model.user.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private UserModel data;
    private int code;
}
