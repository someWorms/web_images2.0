package com.worm.web_images20.controller;

import com.worm.web_images20.dao.JpaTheImageDAO;
import com.worm.web_images20.dao.TheImageDAO;
import com.worm.web_images20.model.TheImage;
import com.worm.web_images20.service.TheImageService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Controller
public class TheImageController {

    @Autowired
    private TheImageService theImageService;

    @PostMapping("/saveImage")
    public ModelAndView save(@ModelAttribute("uploadedImage") TheImage theImage) {
        ModelAndView modelAndView = new ModelAndView();


        /*Save model, it HAVE TO be before persisting*/
        try {
            theImage.saveTheImageToFolder();
        }catch (IOException e){
            e.printStackTrace();
            /*Will show error page if IOException is thrown*/
            modelAndView.setViewName("error");
            return modelAndView;
        }

        /*Persist image*/
        theImageService.save(theImage);

        modelAndView.setViewName("upload");
        modelAndView.addObject("image", theImage);
        return modelAndView;
    }


    @GetMapping("/album")
    public ModelAndView album(){
        ModelAndView modelAndView = new ModelAndView("album");
        modelAndView.addObject("images", theImageService.getAll());
        return modelAndView;
    }

    @GetMapping("/album/showFull/{name}")
    public ModelAndView showData(@PathVariable String name){
        ModelAndView modelAndView = new ModelAndView("showFull");
        modelAndView.addObject("image", theImageService.getByName(name));
        return modelAndView;
    }

    @PostMapping("/album/showFull/{name}")
    public ModelAndView addCom(@PathVariable String name, @RequestParam String comment){

        TheImage theImage = theImageService.getByName(name);
        theImage.setComments(comment);
        theImageService.save(theImage);

        ModelAndView modelAndView = new ModelAndView("showFull");
        modelAndView.addObject("image", theImageService.getByName(name));

        return modelAndView;
    }


}
