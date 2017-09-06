package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.OrderDao;
import org.lanqiao.dao.impl.OrderDaoImpl;
import org.lanqiao.entity.Order;
import org.lanqiao.service.OrderService;

public class OrderServiceImpl implements OrderService {
    OrderDao dao = new OrderDaoImpl();
	@Override
	public void insertOrder(Order order) {
		dao.insert(order);
		
	}
	@Override
	public List<Order> getList() {
		List<Order> list = dao.list();
		return list;
	}
	@Override
	public void updateOrder(Order order) {
		dao.update(order);
		
	}
	@Override
	public void deleteOrder(String id) {
		dao.delete(id);
		
	}
	@Override
	public void changeSum(String id, double sum) {
		dao.totalprice(id, sum);
		
	}

	
}
