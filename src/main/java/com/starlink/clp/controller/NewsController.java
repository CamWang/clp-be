package com.starlink.clp.controller;

import com.starlink.clp.entity.News;
import com.starlink.clp.service.NewsService;
import com.starlink.clp.validate.ValidPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author CamWang
 * @since 2020/9/10 16:08
 */

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@Validated
public class NewsController {

    private NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    @ResponseStatus(HttpStatus.OK)
    public Page<News> getNews(
            @ValidPage(message = "页面请求错误") Pageable pageable
    ){
        return newsService.getNews(pageable);
    }

    @PostMapping("/news")
    @ResponseStatus(HttpStatus.OK)
    public String addNews(
            @Validated News news
    ) {
        this.newsService.addNews(news);
        return "新闻保存成功";
    }
}
