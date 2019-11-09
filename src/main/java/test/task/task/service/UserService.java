package test.task.task.service;

import test.task.task.entity.User;

public interface UserService {
    User findById(String id);
    User insertUser(User user);
}
