package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.User;

public interface UserService {
     public void insertUser(User user);
     //1.拿到登陆是否成功！ 2.获取用户信息(userid,uloginid,upassword...)
     public User login(String loginid,String password);
     public User getUserByLoginId(String loginid);
     public void updateUser(User user);
     public List<User> getUsers();
     public void remove(String id);
}
