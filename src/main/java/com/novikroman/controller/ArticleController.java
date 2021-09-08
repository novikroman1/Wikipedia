package com.novikroman.controller;

import com.novikroman.entity.User;
import com.novikroman.service.UserDetailsServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ArticleController {
    private UserDetailsServiceImpl detailsService;

    public ArticleController(UserDetailsServiceImpl detailsService) {
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
}
