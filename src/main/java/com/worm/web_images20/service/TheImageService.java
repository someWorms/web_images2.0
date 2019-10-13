package com.worm.web_images20.service;

import com.worm.web_images20.model.TheImage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TheImageService {
    public List<TheImage> getAll();
    public void save(TheImage theImage);
    public TheImage getByName(String name);
}
