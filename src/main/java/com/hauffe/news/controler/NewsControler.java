package com.hauffe.news.controler;

import com.hauffe.news.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class NewsControler {

    NewsService service;

    public NewsControler(NewsService service) {
        this.service = service;
    }

    @RequestMapping("")
    public String hello() {
        return "News Service (Alexandre Hauffe)";
    }

    @RequestMapping("test")
    public String test() {
        try {
            return service.addNews();
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
