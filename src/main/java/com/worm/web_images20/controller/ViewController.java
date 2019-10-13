package com.worm.web_images20.controller;

import com.worm.web_images20.model.TheImage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/")
    public String uploadPage(Model model){
        model.addAttribute("image", new TheImage());
        return "upload";
    }
}
