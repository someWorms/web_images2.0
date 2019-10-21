package com.worm.web_images20.valid;

import org.springframework.web.servlet.ModelAndView;
/**
 * Exception which throws if image doesnt have any stream of bytes.
 * */
public class NoImageException extends Exception {
    public NoImageException(){
        ModelAndView modelAndView = new ModelAndView("error");
    }
}
