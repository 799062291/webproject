package org.lanqiao.service.impl;

import org.lanqiao.dao.activationDao;
import org.lanqiao.dao.impl.activationDaoImpl;
import org.lanqiao.service.activationService;

public class activationServiceImpl implements activationService{
	activationDao dao = new activationDaoImpl();
	@Override
	public void activationUser(String id) {
		dao.activation(id);
		
	}

}
