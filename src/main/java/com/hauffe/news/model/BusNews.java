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
@EqualsAndHashCode
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
}
