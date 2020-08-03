package com.worm.web_images20.mapper;

import com.worm.web_images20.Dto.ImageFileDTO;
import com.worm.web_images20.Dto.ImageShowDTO;
import com.worm.web_images20.model.ImageEntity;

import java.util.List;

public interface ImageMapper {
    public ImageEntity convertImageFileDTOtoImageEntity(ImageFileDTO data);
    public List<ImageShowDTO> convertImageEntityToImageShowDto(List<ImageEntity> entities);
    public ImageEntity convertImageShowDTOtoImageEntity(ImageShowDTO imageShowDTO);
}
