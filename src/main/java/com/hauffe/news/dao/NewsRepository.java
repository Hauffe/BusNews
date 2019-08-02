package com.hauffe.news.dao;

import com.hauffe.news.model.BusNews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<BusNews, Long> {
}
