package org.lanqiao.service.impl;

import org.lanqiao.dao.GoodsDao;
import org.lanqiao.dao.impl.GoodsDaoImpl;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.entity.User;
import org.lanqiao.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
    GoodsDao dao = null;
    
	public GoodsServiceImpl() {
		dao = new GoodsDaoImpl();
	}

	@Override
	public PageInfo<Goods> GoodsList(String cid, int pageSize, int pageIndex) {
		PageInfo<Goods> pageInfo = dao.list(cid, pageSize, pageIndex);
		return pageInfo;
	}

	@Override
	public Goods getGoodsById(String id) {
		Goods goods = dao.get(id);
		return goods;
	}

	@Override
	public void removeGoods(String id) {
		dao.remove(id);
		
	}

	@Override
	public void updateGoods(Goods goods) {
		dao.update(goods);
		
	}

	@Override
	public void insertGoods(Goods goods) {
		dao.insert(goods);
		
	}

	@Override
	public void changeGoods(Goods goods) {
		dao.changeImg(goods);
		
	}

	@Override
	public PageInfo<Goods> GoodsList1(String key, int pageSize, int pageIndex) {
		PageInfo<Goods> pageInfo = dao.list1(key, pageSize, pageIndex);
		return pageInfo;
		
	}

}
