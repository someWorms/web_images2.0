package com.worm.web_images20.controller;


import com.worm.web_images20.model.TheImage;
import com.worm.web_images20.service.TheImageService;
import com.worm.web_images20.valid.ImageValidator;
import com.worm.web_images20.valid.NoImageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
/**
 * Main controller. Works with image model,
 * */

@Controller
public class TheImageController {

    @Autowired
    private TheImageService theImageService;

    /**
     * Validation, saving and persisting image
     * @param theImage Gets all data from html, and map it to object.
     * */
    @PostMapping("/saveImage")
    public ModelAndView save(@ModelAttribute("uploadedImage") TheImage theImage) {
        ModelAndView modelAndView = new ModelAndView();
        ImageValidator imageValidator = new ImageValidator();


        /**
         * If it is possible to bypass html required tag, so there will be thrown
         * an exception and return "error" as view page
         * @throws NoImageException
         * */
        try {
            if(theImage == null || theImage.getMyFile().getBytes() == null){
                throw new NoImageException();
            }
        }catch (Exception e){
            modelAndView.setViewName("error");
            return modelAndView;
        }

        /**
         * Validate image for any image type using mime-type.
         * @throws Exception
         * */

        try {
            imageValidator.isImage(theImage);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            return modelAndView;
        }


        /**
         * Saves the image to folder, with an unique name
         * @throws IOException
         * */
        try {
            theImage.saveTheImageToFolder();
        }catch (IOException e){
            e.printStackTrace();
            /*Will show error page if IOException is thrown*/
            modelAndView.setViewName("error");
            return modelAndView;
        }

        /**
         * Persist all image's metadata to DataBase
         * */
        theImageService.save(theImage);

        modelAndView.setViewName("upload");
        modelAndView.addObject("image", theImage);
        return modelAndView;
    }


    /**
     * Album, shows all images, previously uploaded
     * */
    @GetMapping("/album")
    public ModelAndView album(){
        ModelAndView modelAndView = new ModelAndView("album");
        modelAndView.addObject("images", theImageService.getAll());
        return modelAndView;
    }

    /**
     * Shows full image, and full list of comments.
     * @param name image name, which is used to get image from database
     * */
    @GetMapping("/album/showFull/{name}")
    public ModelAndView showData(@PathVariable String name){
        ModelAndView modelAndView = new ModelAndView("showFull");
        modelAndView.addObject("image", theImageService.getByName(name));
        return modelAndView;
    }


    /**
     * Adds commentary to list of proper image
     * @param name image name, which is used to get image from database
     * @param comment store commentary which will be added to list
     * @return showFull page
     * */
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
