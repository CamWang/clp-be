package com.starlink.clp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CamWang
 * @since 2020/9/8 10:53
 */
//@RestController
//@RequestMapping("/error")
public class ErrorController {

    @RequestMapping
    public String error() {
        return "出错啦";
    }
}
