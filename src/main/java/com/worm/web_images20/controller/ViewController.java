package com.worm.web_images20.controller;

import com.worm.web_images20.Dto.ImageFileDTO;
import com.worm.web_images20.Dto.UserDTO;
import com.worm.web_images20.mapper.UserMapper;
import com.worm.web_images20.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
    private final UserService userService;
    private final UserMapper userMapper;

    @RequestMapping("/")
    public ModelAndView uploadPage(Model model){
        model.addAttribute("image", new ImageFileDTO());
        return new ModelAndView("upload");
    }


    @RequestMapping("/register")
    public ModelAndView register(){

        return new ModelAndView("register");
    }

    @PostMapping("/newUser")
    public ModelAndView newUser(UserDTO user){
        // for extension
        userService.save(userMapper.convertUserDtoToUser(user));
        return new ModelAndView("register");
    }

    public ViewController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
}
