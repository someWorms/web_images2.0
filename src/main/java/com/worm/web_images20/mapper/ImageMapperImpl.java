package com.worm.web_images20.mapper;

import com.worm.web_images20.Dto.ImageFileDTO;
import com.worm.web_images20.Dto.ImageShowDTO;
import com.worm.web_images20.model.ImageEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ImageMapperImpl implements ImageMapper{
    @Override
    public ImageEntity convertImageFileDTOtoImageEntity(ImageFileDTO data) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileName(data.getFile().getOriginalFilename());
        imageEntity.getComments().add(data.getCommentary());
        imageEntity.setMultipartFile(data.getFile());
        return imageEntity;
    }

    @Override
    public List<ImageShowDTO> convertImageEntityToImageShowDto(List<ImageEntity> entities) {
        List<ImageShowDTO> dtos = new ArrayList<>();
        for (ImageEntity e:
             entities) {
            ImageShowDTO showDTO = new ImageShowDTO();
            showDTO.setPathAndName(e.getPathAndName());
            showDTO.setUuid(e.getUuid());
            showDTO.setImageName(e.getFileName());
            showDTO.setComments(e.getComments());
            dtos.add(showDTO);
        }

        return dtos;
    }

    @Override
    public ImageEntity convertImageShowDTOtoImageEntity(ImageShowDTO imageShowDTO) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setPathAndName(imageShowDTO.getPathAndName());
        imageEntity.setUuid(imageShowDTO.getUuid());
        imageEntity.setFileName(imageShowDTO.getImageName());
        imageEntity.setComments(imageShowDTO.getComments());
        return imageEntity;
    }

    @Override
    public ImageShowDTO convertImageEntityToOneImageShowDto(ImageEntity imageEntity) {
        ImageShowDTO showDTO = new ImageShowDTO();
        showDTO.setPathAndName(imageEntity.getPathAndName());
        showDTO.setUuid(imageEntity.getUuid());
        showDTO.setImageName(imageEntity.getFileName());
        showDTO.setComments(imageEntity.getComments());
        return showDTO;
    }
}
