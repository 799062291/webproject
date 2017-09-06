package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.NewsDao;
import org.lanqiao.dao.impl.NewsDaoImpl;
import org.lanqiao.entity.News;
import org.lanqiao.service.NewsService;

public class NewsServiceImpl implements NewsService {  //新闻service层接口实现类，主要调用dao层的方法，返回数据库里的数据
     NewsDao dao = null;   //创建dao层对象
     public NewsServiceImpl() {
    	
		dao = new NewsDaoImpl();
	}
     
	@Override
	public List<News> newList() {   //通过对象调用dao层里的方法，返回结果
		List<News>list1 =  dao.list();
		return list1;
	}

	@Override
	public News getNewsById(String id) {
		News new1 = dao.get(id);
		return new1;
	}

}
