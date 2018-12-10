package com.zzg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classname helloController
 * @desc TODO
 * @author: zzg
 * @date: 2018/12/7 16:27
 */
@RequestMapping("/hello")
@RestController
public class helloController {

    @GetMapping("/word")
    public String test(){
        return "hello word ~~";
    }
}
