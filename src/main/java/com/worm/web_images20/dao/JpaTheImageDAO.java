package com.worm.web_images20.dao;

import com.worm.web_images20.model.TheImage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class JpaTheImageDAO implements TheImageDAO{
    @PersistenceContext
    private EntityManager entityManager;

    /*Persist to database*/
    @Transactional
    public void save(TheImage theImage){
        entityManager.persist(theImage);
    }

}
