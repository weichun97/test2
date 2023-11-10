package com.github.weichun97.config;

import com.github.weichun97.cxf.HelloWorldService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.xml.ws.Endpoint;

/**
 * @author chun
 * @date 2023/11/9 16:08
 */
@Configuration
public class CxfServerConfig {

    /**
     * 指定beanName,防止和客户端注入的bean冲突
     */
    @Resource(name = "serverHelloWorldService")
    private HelloWorldService helloWorldService;

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    /**
     * 服务发布
     * @return
     */
    @Bean
    public Endpoint helloWorldServiceEndpoint() {
        Endpoint endpoint = Endpoint.create(helloWorldService);
        endpoint.publish("/helloWorld");//发布地址
        return endpoint;
    }
}
