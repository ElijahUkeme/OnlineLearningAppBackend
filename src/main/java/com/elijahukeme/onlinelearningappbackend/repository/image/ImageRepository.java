package com.elijahukeme.onlinelearningappbackend.repository.image;

import com.elijahukeme.onlinelearningappbackend.model.image.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel,String> {
}
