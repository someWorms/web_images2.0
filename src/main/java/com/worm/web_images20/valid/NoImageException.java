package com.worm.web_images20.valid;

import org.springframework.web.servlet.ModelAndView;

public class NoImageException extends Exception {
    public NoImageException(){
        ModelAndView modelAndView = new ModelAndView("error");
    }
}
