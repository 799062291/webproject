package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.Order;

public interface OrderDao {
    public void insert(Order order);
    public List<Order> list();
    public void update(Order order);
    public void delete(String id);
    public void totalprice(String id,double sum);
}
