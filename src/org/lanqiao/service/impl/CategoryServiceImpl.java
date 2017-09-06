package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.CategoryDao;
import org.lanqiao.dao.impl.CategoryDaoImpl;
import org.lanqiao.entity.Category;
import org.lanqiao.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao cate = new CategoryDaoImpl();
	@Override
	public List<Category> categoryList() {
		List<Category> list = cate.list();
		return list;
	}
	@Override
	public Category getCate(String id) {
		Category cates = cate.get(id);
		return cates;
	}

}
