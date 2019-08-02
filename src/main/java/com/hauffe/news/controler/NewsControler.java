package com.hauffe.news.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class NewsControler {

    @RequestMapping("")
    public String hello() {
        return "News Service (Alexandre Hauffe)";
    }

}
