package com.imooc.firstdemo.controller;

import com.imooc.firstdemo.domain.User;
import com.imooc.firstdemo.repository.UserRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    private final UserRespository userRespository;

    @Autowired
    public UserController(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @PostMapping("/person/save")
    private User save(@RequestParam("name") String name){
        User user = new User();
        user.setName(name);
        if(userRespository.save(user)) {
            log.info("【保存用户对象】user={}", user);
        }

        return user;
    }
}
