package com.gao.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gao.demo.entity.UserEntity;
import com.gao.demo.service.UserService;


@RestController

public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/meet")
    public String meet(){
        return "{\"str\":\"I meet you\"}";
    }

    @GetMapping("/welcome")
    public String welcome(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "{\"str\":\"welcome"+ authentication.getName()+"\"}";
    }

    @GetMapping("/project")
    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    public String project(){
        return "{\"str\":\"this is admin project\"}";
    }
    
    @GetMapping("/login")
    public String login(){
        return "message url defined in websecurityConfig";
    }

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(@RequestBody UserEntity vo) throws Exception {
        UserEntity u = new UserEntity();
        u.setUsername(vo.getUsername());
        u.setPassword(vo.getPassword());
        u.setUserType(vo.getUserType());
        u.setEmail(vo.getEmail());
        u.setMobile(vo.getMobile());
        u.setConfirmed(0);
        this.userService.save(u);
        return "Add user successfully!";
    }
    
}
