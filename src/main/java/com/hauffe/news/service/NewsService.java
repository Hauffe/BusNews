package com.hauffe.news.service;

import com.hauffe.news.dao.NewsRepository;
import com.hauffe.news.model.BusNews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {

    NewsRepository repository;
    RestTemplate restTemplate;
    Document doc;


    public NewsService(NewsRepository repository) {
        this.repository = repository;
        restTemplate = new RestTemplate();
    }

    public BusNews newBusNews() {
        return repository.save(new BusNews());
    }

    public BusNews readNewsFromUrbsPage() throws Exception{

        return null;
    }
}
