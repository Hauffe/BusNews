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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NewsService {

    private final NewsRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private Document doc;

    @Autowired
    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }

    public List<BusNews> getNews(){
        Iterable<BusNews> iterableNews = repository.findAll();
        List<BusNews> newsFromDB = new ArrayList<>();
        iterableNews.forEach(newsFromDB::add);
        Collections.reverse(newsFromDB);
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
            String link = headline.select(Constants.LINK_QUERY.getValue()).attr("href");
            if(!link.isEmpty()){
                if(link.substring(0,3).equals("../")){
                    busNews.setLink(Constants.URBS_HOME_PAGE.getValue() + link.substring(15));
                }else{
                    busNews.setLink(Constants.URBS_HOME_PAGE.getValue());
                }
            }else busNews.setLink(Constants.URBS_HOME_PAGE.getValue());

            busNews.setImageURL(headline.select(Constants.IMAGE_URL_QUERY.getValue()).attr("src"));
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
