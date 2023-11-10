package com.github.weichun97.controller;

import com.github.weichun97.cxf.HelloWorldService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chun
 * @date 2023/11/7 11:32
 */
@RestController
@RequestMapping
public class TestController {

    /**
     * 指定客户端bean，防止和服务端的冲突
     */
    @Resource(name = "clientHelloWorldService")
    private HelloWorldService helloWorldService;

    @GetMapping("test")
    public String test(){
        return helloWorldService.sayHello("张三");
    }
}
