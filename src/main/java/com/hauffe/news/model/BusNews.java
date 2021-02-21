package com.hauffe.news.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "BUS_NEWS")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BusNews {

    @Id
    @GeneratedValue
    @JsonIgnore
    @Column(name = "ID")
    @Getter @Setter private Long id;
    @Column(name = "TITLE")
    @Getter @Setter private String title;
    @Column(name = "DATE")
    @Getter @Setter private Date date;
    @Column(name = "CONTENT", columnDefinition="TEXT")
    @Getter @Setter private String content;
    @Column(name = "LINK")
    @Getter @Setter private String link;
    @Column(name = "IMAGE_URL")
    @Getter @Setter private String imageURL;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusNews busNews = (BusNews) o;
        return Objects.equals(id, busNews.id) &&
                Objects.equals(title, busNews.title) &&
                Objects.equals(date, busNews.date) &&
                Objects.equals(content, busNews.content) &&
                Objects.equals(link, busNews.link) &&
                Objects.equals(imageURL, busNews.imageURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, content, link, imageURL);
    }
}
