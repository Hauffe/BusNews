package com.hauffe.news.service;

import com.hauffe.news.components.ScheduledTasks;
import com.hauffe.news.dao.NewsRepository;
import com.hauffe.news.model.BusNews;
import com.hauffe.news.utils.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NewsService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private NewsRepository repository;
    private RestTemplate restTemplate;
    private Document doc;


    public NewsService(NewsRepository repository) {
        this.repository = repository;
        restTemplate = new RestTemplate();
    }

    public List<BusNews> getNews(){
        Iterable<BusNews> iterableNews = repository.findAll();
        List<BusNews> newsFromDB = new ArrayList<>();
        iterableNews.forEach(newsFromDB::add);
        return newsFromDB;
    }

    public List<BusNews> readNews() throws Exception{

        List<BusNews> newNews = new ArrayList<>();
        BusNews busNews;

        doc = Jsoup.connect(Constants.URBS_URL.getValue()).get();
        Elements newsHeadlines = doc.select(Constants.NEWS_QUERY.getValue());

        for (Element headline : newsHeadlines) {
            busNews = new BusNews();
            busNews.setTitle(headline.select(Constants.TITLE_QUERY.getValue()).text());

            busNews.setDate(new SimpleDateFormat(Constants.DATE_FORMAT.getValue())
                    .parse(headline.select(Constants.DATE_QUERY.getValue()).text()));

            busNews.setContent(headline.select(Constants.CONTENT_QUERY.getValue()).text());
            busNews.setLink(headline.select(Constants.LINK_QUERY.getValue()).attr("href"));
            busNews.setImageURL(headline.select(Constants.IMAGE_URL_QUERY.getValue()).text());
            newNews.add(busNews);
        }
        return newNews;
    }

    public void updatingNewsDB(){
        boolean newNewsFound = false;

        try {
            List<BusNews> newsFromDB = this.getNews();
            List<BusNews> urbsNews = this.readNews();
            if(newsFromDB.size() == 0){
                Collections.reverse(urbsNews);
                repository.saveAll(urbsNews);
                logger.info(Constants.NEW_NEWS_FOUND.getValue() + urbsNews.toString());
            }else{
                for(BusNews busNews : urbsNews){
                    for(BusNews dbNews : newsFromDB){
                        if(!Objects.equals(busNews.getTitle(), dbNews.getTitle())){
                            newNewsFound = true;
                        }else{
                            newNewsFound = false;
                            break;
                        }
                    }
                    if(newNewsFound){
                        repository.save(busNews);
                        logger.info(Constants.NEW_NEWS_FOUND.getValue() + busNews.toString());
                    }
                }

            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
