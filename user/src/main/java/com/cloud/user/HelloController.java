package com.cloud.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="hello")
public class HelloController {

    @Value("${classic}")
    private String classic;
    @RequestMapping(value="classic")
    private String hello(){
        return classic;
    }

}
