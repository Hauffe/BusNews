package com.hauffe.news.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "BUS_NEWS")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusNews {

    @Id
    @GeneratedValue
    @JsonIgnore
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DATE")
    private Date date;
    @Column(name = "CONTENT", columnDefinition="TEXT")
    private String content;
    @Column(name = "LINK")
    private String link;
    @Column(name = "IMAGE_URL")
    private String imageURL;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusNews busNews = (BusNews) o;
        return Objects.equals(title, busNews.title) && Objects.equals(date, busNews.date) && Objects.equals(content, busNews.content) && Objects.equals(link, busNews.link) && Objects.equals(imageURL, busNews.imageURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date, content, link, imageURL);
    }
}
