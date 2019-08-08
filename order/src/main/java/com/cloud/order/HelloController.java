//package com.cloud.order;
//
//import com.cloud.framework.base.model.ConsulConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value="hello")
//public class HelloController {
//
//    @Value("${classic}")
//    private String classic;
//
//    @Autowired
//    private ConsulConfig consulConfig;
//
//    @RequestMapping(value="classic")
//    private String hello1(){
//        return classic;
//    }
//
//
//    @RequestMapping(value="config")
//    private ConsulConfig hello2(){
//        return consulConfig;
//    }
//
//}
