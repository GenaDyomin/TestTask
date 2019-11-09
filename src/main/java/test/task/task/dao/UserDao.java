package test.task.task.dao;

import test.task.task.entity.User;

public interface UserDao {

     User findById(String id);
     User insertUser(User user);
}
