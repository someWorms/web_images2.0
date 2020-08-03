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

    /*
    * Found out that passing object from th frontend not a good idea, and tg does not allow do it good
    * so i use uuid instead.
    * */


    @GetMapping("/album/showFull/{uuid}")
    public ModelAndView showData(@PathVariable String uuid){

        this.modelAndView.setViewName("showFull");
        this.modelAndView.addObject("image",mapper.convertImageEntityToOneImageShowDto(imageService.getByUuid(uuid)));
        return modelAndView;
    }


    
    @PostMapping("/album/showFull/{uuid}")
    public ModelAndView addCom(@PathVariable String uuid, @RequestParam String comment){
        System.out.println("aaaaaaaaaaa");
        //get dto by uuid
        ImageEntity updated = imageService.getByUuid(uuid);
        //add shit to dto
        updated.getComments().add(comment);
        System.out.println("vvvvvvvvvv");
        //save entity
        this.imageService.update(updated);



        this.modelAndView.setViewName("showFull");
        this.modelAndView.addObject("image", this.mapper.convertImageEntityToOneImageShowDto(imageService.getByUuid(uuid)));

        return this.modelAndView;
    }


    public ImageController(ImageServiceImpl imageService, ImageMapperImpl mapper) {
        this.imageService = imageService;
        this.mapper = mapper;
    }
}
