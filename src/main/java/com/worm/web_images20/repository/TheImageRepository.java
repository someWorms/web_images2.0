package com.worm.web_images20.repository;

import com.worm.web_images20.model.TheImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * JpaRepository.
 * */
@Repository
public interface TheImageRepository extends JpaRepository<TheImage, Long> {
    TheImage findByName(String name);
}
