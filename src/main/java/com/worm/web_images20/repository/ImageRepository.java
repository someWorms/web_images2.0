package com.worm.web_images20.repository;

import com.worm.web_images20.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * JpaRepository.
 * */
@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    ImageEntity findByUuid(String uuid);
}
