package com.novikroman.controller;

import com.novikroman.entity.User;
import com.novikroman.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserController {
    private UserDetailsServiceImpl detailsService;

    @Autowired
    public UserController(UserDetailsServiceImpl detailsService) {
        this.detailsService = detailsService;
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody User user) throws Exception {
        return detailsService.authenticate(user);
    }

    @PostMapping( "/registration")
    public void register(@RequestBody final User dto) {
        detailsService.saveUser(dto);
    }
    @PostMapping( "/")
    public void test(@RequestBody final Object dto) {
        System.out.println("Object");
    }
}
