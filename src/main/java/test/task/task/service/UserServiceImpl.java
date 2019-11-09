package test.task.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.task.dao.UserDao;
import test.task.task.entity.User;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    public UserDao userDao;

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public User insertUser(User user) {
        return userDao.insertUser(user);
    }
}
