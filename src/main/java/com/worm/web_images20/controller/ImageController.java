package com.worm.web_images20.controller;


import com.worm.web_images20.Dto.ImageFileDTO;
import com.worm.web_images20.Dto.ImageShowDTO;
import com.worm.web_images20.mapper.ImageMapper;
import com.worm.web_images20.mapper.ImageMapperImpl;
import com.worm.web_images20.model.ImageEntity;
import com.worm.web_images20.service.ImageService;
import com.worm.web_images20.service.ImageServiceImpl;
import com.worm.web_images20.valid.ImageValidator;
import com.worm.web_images20.valid.NoImageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ImageController {
    private final ImageService imageService;
    private final ImageMapper mapper;
    private ModelAndView modelAndView = new ModelAndView();


    @PostMapping("/saveImage")
    public ModelAndView save(@ModelAttribute("uploadedImage") ImageFileDTO file) {
        ImageValidator imageValidator = new ImageValidator();


        try {
            if(file.getFile() == null || file.getFile().getBytes() == null){
                throw new NoImageException();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        try {
            imageValidator.isImage(file.getFile());
        } catch (Exception e) {
            this.modelAndView.setViewName("error");
            return this.modelAndView;
        }


        this.imageService.save(this.mapper.convertImageFileDTOtoImageEntity(file));

        this.modelAndView.setViewName("upload");
        this.modelAndView.addObject("image", file);
        return this.modelAndView;
    }


    @GetMapping("/album")
    public ModelAndView album(){

        List<ImageShowDTO> data = this.mapper.convertImageEntityToImageShowDto(this.imageService.getAll());

        this.modelAndView.setViewName("album");
        this.modelAndView.addObject("images", data);
        return this.modelAndView;
    }


    @PostMapping("/album/showFull")
    public ModelAndView showData(@ModelAttribute ImageShowDTO image){

        this.modelAndView.setViewName("showFull");
        this.modelAndView.addObject("image", image);
        return modelAndView;
    }


    
    @PostMapping("/album/showFull/add")
    public ModelAndView addCom(@ModelAttribute ImageShowDTO image, @RequestParam String comment){
        System.out.println(image.getImageName());
        image.getComments().add(comment);
        this.imageService.update(this.mapper.convertImageShowDTOtoImageEntity(image));


        this.modelAndView.setViewName("showFull");
        this.modelAndView.addObject("image", image);

        return this.modelAndView;
    }


    public ImageController(ImageServiceImpl imageService, ImageMapperImpl mapper) {
        this.imageService = imageService;
        this.mapper = mapper;
    }
}
