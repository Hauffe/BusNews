package com.hauffe.news.components;

import com.hauffe.news.service.NewsService;
import com.hauffe.news.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private NewsService service;

    public ScheduledTasks(NewsService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        try{
            TimeUnit.SECONDS.sleep(5);
            logger.info(Constants.UPDATE_TIME.getValue());
            service.updatingNewsDB();
            logger.info(Constants.EXECUTION_TIME.getValue(), dateTimeFormatter.format(LocalDateTime.now()));
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
