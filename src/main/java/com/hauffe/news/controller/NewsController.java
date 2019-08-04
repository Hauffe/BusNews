package com.hauffe.news.controller;

import com.hauffe.news.components.ScheduledTasks;
import com.hauffe.news.model.BusNews;
import com.hauffe.news.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class NewsController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }

    @RequestMapping("")
    public String hello() {
        return "News Service (Version 1.0) created by Alexandre Hauffe";
    }

    @RequestMapping("test")
    public String test() {
        try {
            return service.readNews().toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping("bus_news")
    public ResponseEntity<List<BusNews>> retrieveNews(){
        try{
            return ResponseEntity.ok(service.deliverNews());
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

}
