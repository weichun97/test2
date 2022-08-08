package com.github.weichun97.openfeigntest.controller;

import com.github.weichun97.openfeigntest.api.SysApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    SysApi sysApi;

    @GetMapping("")
    public void test(){
        String s = sysApi.deleteById(1L);
    }
}
