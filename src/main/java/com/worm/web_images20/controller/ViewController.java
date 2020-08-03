package com.worm.web_images20.controller;

import com.worm.web_images20.Dto.ImageFileDTO;
import com.worm.web_images20.model.ImageEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
    @RequestMapping("/")
    public ModelAndView uploadPage(Model model){
        model.addAttribute("image", new ImageFileDTO());
        return new ModelAndView("upload");
    }
}
