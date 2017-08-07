package com.txl.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TeaBee on 2017/7/27.
 */
@RestController
public class HelloController {

    @RequestMapping(method = RequestMethod.GET , value = "/hello")
    public String getHello(){
        return "hello";
    }
}
