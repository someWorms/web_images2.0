package com.worm.web_images20.service;

import com.worm.web_images20.model.TheImage;
import com.worm.web_images20.repository.TheImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class TheImageServiceImpl implements TheImageService {

    @Autowired
    TheImageRepository theImageRepository;
    @Override
    public List<TheImage> getAll() {
        return theImageRepository.findAll();
    }


    @Transactional
    public void save(TheImage theImage){
        theImageRepository.save(theImage);
    }

    @Override
    public TheImage getByName(String name) {
        return theImageRepository.findByName(name);
    }
}
