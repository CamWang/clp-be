package com.starlink.clp.service;

import com.starlink.clp.entity.News;
import com.starlink.clp.repository.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author CamWang
 * @since 2020/9/10 16:04
 */
@Service
public class NewsService {

    private NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Page<News> getNews(Pageable pageable) {
        return this.newsRepository.findBy(pageable);
    }

    public News addNews(News news) {
        news.setId(null);
        return newsRepository.save(news);
    }
}
