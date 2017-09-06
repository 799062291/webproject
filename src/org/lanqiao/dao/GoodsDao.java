package org.lanqiao.dao;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

public interface GoodsDao {
    public PageInfo<Goods> list(String cid,int pageSize, int pageIndex);
    public int totalRecords(String id);
    public Goods get(String id);
    public void remove(String id);
    public void update(Goods goods);
    public void insert(Goods goods);
    public void changeImg(Goods goods);
    public PageInfo<Goods> list1(String key,int pageSize, int pageIndex);
	public int totalRecords1(String id);
}
