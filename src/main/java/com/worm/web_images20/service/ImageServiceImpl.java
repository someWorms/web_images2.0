package com.worm.web_images20.service;

import com.worm.web_images20.model.ImageEntity;
import com.worm.web_images20.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
//@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private static final Path ABSOLUTE_PATH = Paths.get(".").toAbsolutePath();
    private static final String FILE_FOLDER = "/images/";


    @Override
    public List<ImageEntity> getAll() {
        return this.imageRepository.findAll();
    }


    @Transactional
    public void save(ImageEntity imageEntity){
        checkFolder();
        imageEntity.setPathAndName(ABSOLUTE_PATH + FILE_FOLDER + imageEntity.getFileName());
        imageEntity.setUuid(UUID.randomUUID().toString());

        Path path = Paths.get(ABSOLUTE_PATH + FILE_FOLDER + imageEntity.getMultipartFile().getOriginalFilename());
        try {
            Files.write(path, imageEntity.getMultipartFile().getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
        this.imageRepository.save(imageEntity);
    }


    @Override
    public ImageEntity getByName(String name) {
        return this.imageRepository.findByFileName(name);
    }

    @Override
    public ImageEntity getOneImage(ImageEntity imageEntity) {
        if (imageEntity.getId() < 0) {
            return null;
        }

        return imageRepository.getOne(imageEntity.getId());
    }

    @Override
    public ImageEntity update(ImageEntity imageEntity) {
        return this.imageRepository.save(imageEntity);
    }

    private void checkFolder() {
        try {
            if(!(new File(ABSOLUTE_PATH + FILE_FOLDER).exists()))
                new File(ABSOLUTE_PATH + FILE_FOLDER).mkdir();
        }catch (Exception e){
            e.printStackTrace();

        }
    }

/*    private String _nameGenerator(String name) {
        long _slugNumber = new Date().getTime();
        name = (_slugNumber+name);
        return name;
    }*/

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
}
