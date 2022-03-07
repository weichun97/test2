package com.github.weichun97.mysqlreplicationtest.controller;

import com.github.weichun97.mysqlreplicationtest.entity.po.User;
import com.github.weichun97.mysqlreplicationtest.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chun
 * @date 2022/3/7 15:16
 */
@RestController
public class TestController {

    @Resource
    private UserService userService;

    @GetMapping
    public void test(){
        userService.save(User.builder()
                .name("张三")
                .build());
        User user = userService.getById(1);
        System.out.println(user);
    }
}
