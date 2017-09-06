package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.User;


public interface UserDao {
   public void insert(User user);
   public User getUserByLoginId(String loginid);
   public void update(User user);
   public List<User> userList();
   public void removeUser(String id);

}
