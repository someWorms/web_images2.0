package com.worm.web_images20.controller;

import com.worm.web_images20.model.TheImage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * View controller, shows root page.
 * @param model store view name
 * */
@Controller
public class ViewController {
    @RequestMapping("/")
    public ModelAndView uploadPage(Model model){
        model.addAttribute("image", new TheImage());
        return new ModelAndView("upload");
    }
}
