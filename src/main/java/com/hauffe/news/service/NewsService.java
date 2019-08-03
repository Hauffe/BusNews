package com.hauffe.news.service;

import com.hauffe.news.dao.NewsRepository;
import com.hauffe.news.model.BusNews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {

    NewsRepository repository;
    RestTemplate restTemplate;
    Document doc;

    String URBS_URL = "https://www.urbs.curitiba.pr.gov.br/transporte/boletim-de-transportes";


    public NewsService(NewsRepository repository) {
        this.repository = repository;
        restTemplate = new RestTemplate();
    }

    public BusNews newBusNews() {
        return repository.save(new BusNews());
    }

    public String addNews() throws Exception{
        String test = " ";
        doc = Jsoup.connect(URBS_URL).get();
        Elements newsHeadlines = doc.select("#main > div.leftCol > div.bg-white");
        for (Element headline : newsHeadlines) {
            test += "Titulo: "+headline.select("h2").text() + "/";
            test += "Date: "+headline.select("p.resize > span").text() + "/";
            test += "Content: "+headline.select("p").text() + "/";
            test += "link: "+headline.select("a").attr("href") + "/";
            test += "imageURL: "+headline.select("a > img").attr("src") + "------------";
        }
        return test;
    }
}
