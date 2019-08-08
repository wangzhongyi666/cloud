package com.cloud.order;

import com.cloud.frameworkbase.model.ConsulConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*@EnableEurekaClient*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({ConsulConfig.class})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
