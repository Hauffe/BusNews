package com.hauffe.news.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Constants {
    URBS_HOME_PAGE("https://www.urbs.curitiba.pr.gov.br/"),
    URBS_URL("https://www.urbs.curitiba.pr.gov.br/transporte/boletim-de-transportes"),
    NEWS_QUERY("#main > div.col-12 > div.bg-white"),
    TITLE_QUERY("h2"),
    DATE_QUERY("p.resize > span"),
    CONTENT_QUERY("p"),
    LINK_QUERY("a"),
    IMAGE_URL_QUERY("p > a > img"),

    DATE_FORMAT("dd/MM/yyyy"),

    /*-----LOGS-----*/
    EXECUTION_TIME(":: Execution completed! Time - {}"),
    UPDATE_TIME("Time to update"),
    NEW_NEWS_FOUND("NEW NEWS! ");

    @Getter private final String value;

}
