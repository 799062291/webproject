package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.News;

public interface NewsDao {    //dao层接口
    public List<News> list();
    public News get(String id);
}
