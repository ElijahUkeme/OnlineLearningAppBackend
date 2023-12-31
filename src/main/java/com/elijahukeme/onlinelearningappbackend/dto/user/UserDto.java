package com.elijahukeme.onlinelearningappbackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private int type;
    private String name;
    private String email;
    private String profileImage;
    private String accessToken;
}
