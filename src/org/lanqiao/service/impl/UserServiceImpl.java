package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.UserDao;
import org.lanqiao.dao.impl.UserDaoImpl;
import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
	@Override
	public void insertUser(User user) {
		dao.insert(user);

	}
	@Override
	public User login(String loginid, String password) {
		User curruser = dao.getUserByLoginId(loginid);
		User user = null;
		if(curruser==null){  //没有找到该账号，返回空
			return null;
		}
		if(curruser!=null){  //验证密码
			if(curruser.getUpassword().equals(password)){
				user = curruser;
				return user;     //若验证正确，则返回该用户相关信息
			}
		}
		return null;
	}
	@Override
	public User getUserByLoginId(String loginid) {
		User curruser = dao.getUserByLoginId(loginid);
		return curruser;
	}
	@Override
	public void updateUser(User user) {
		dao.update(user);
		
	}
	@Override
	public List<User> getUsers() {
	    List<User> list = dao.userList();
		return list;
	}
	@Override
	public void remove(String id) {
		dao.removeUser(id);
		
	}

}
