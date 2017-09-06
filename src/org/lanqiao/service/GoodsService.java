package org.lanqiao.service;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.entity.User;
import org.omg.CORBA.PUBLIC_MEMBER;

public interface GoodsService {
     public PageInfo<Goods> GoodsList(String cid,int pageSize, int pageIndex);
     public Goods getGoodsById(String id);
     public void removeGoods(String id);
     public void updateGoods(Goods goods);
     public void insertGoods(Goods goods);
     public void changeGoods(Goods goods);
     public PageInfo<Goods> GoodsList1(String key,int pageSize, int pageIndex); //全局获取数据
     
}
