package com.hauffe.news.model;

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
    @Column(name = "CART_ID")
    @Getter @Setter private Long id;
    @Getter @Setter private String title;
    @Getter @Setter private Date date;
    @Getter @Setter private String content;
    @Getter @Setter private String link;
    @Getter @Setter private String imageURL;

}
