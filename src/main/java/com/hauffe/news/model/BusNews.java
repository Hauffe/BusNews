package com.hauffe.news.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

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

}
