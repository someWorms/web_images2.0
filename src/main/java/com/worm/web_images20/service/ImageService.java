package com.worm.web_images20.service;

import com.worm.web_images20.model.ImageEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface ImageService {
    List<ImageEntity> getAll();
    void save(ImageEntity imageEntity);
    ImageEntity getOneImage(ImageEntity imageEntity);
    ImageEntity update(ImageEntity imageEntity);
    ImageEntity getByUuid(String uuid);
}
