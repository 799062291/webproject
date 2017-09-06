package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.News;

public interface NewsService {   //新闻service层接口
    public List<News> newList();
    public News getNewsById(String id);
}
