package com.worm.web_images20.dao;

import com.worm.web_images20.model.TheImage;
import org.springframework.web.multipart.MultipartFile;

public interface TheImageDAO {
    void save(TheImage theImage);
}
