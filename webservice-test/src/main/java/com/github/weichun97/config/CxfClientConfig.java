package com.github.weichun97.config;

import com.github.weichun97.cxf.HelloWorldService;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author chun
 * @date 2023/11/9 16:08
 */
@Configuration
public class CxfClientConfig {

    @Value("${itran.webservice.helloWorld}")
    private String helloWorldServiceUrl;

    @Resource
    private SpringBus bus;

    @Bean("clientHelloWorldService")
    public HelloWorldService helloWorldService() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        // 设置 UserService 接口
        jaxWsProxyFactoryBean.setServiceClass(HelloWorldService.class);
        // 设置 Web Services 地址
        jaxWsProxyFactoryBean.setAddress(helloWorldServiceUrl);
        // 使用已有的bus, 防止覆盖服务端的bus
        jaxWsProxyFactoryBean.setBus(bus);
        // 创建
        return (HelloWorldService) jaxWsProxyFactoryBean.create();
    }
}
