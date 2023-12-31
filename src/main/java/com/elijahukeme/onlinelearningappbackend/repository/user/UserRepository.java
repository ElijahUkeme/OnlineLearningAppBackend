package com.elijahukeme.onlinelearningappbackend.repository.user;

import com.elijahukeme.onlinelearningappbackend.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    UserModel findByAccessToken(String accessToken);
    UserModel findByEmail(String email);
}
