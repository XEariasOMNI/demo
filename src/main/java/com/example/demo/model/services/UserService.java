package com.example.demo.model.services;

import com.example.demo.model.repositories.user.User;
import com.example.demo.model.repositories.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User findById (long id) {
        try {
            User user = userDao.findById(id).orElseThrow();

            return user;
        } catch (NoSuchElementException e) {
            throw e;
            // TODO:
        } catch (Exception e) {
            throw e;
        }
    }

    public List<User> findAll() {
        try {
            List<User> users = ((List<User>) userDao.findAll());

            return users;
        } catch (Exception e) {
            throw e;
        }
    }

    public User create(User user) {
        try {
            User newUser = userDao.save(user);

            return newUser;
        } catch (Exception e) {
            throw e;
        }
    }

    public User update(User user) {
        try {
            return userDao.save(user);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean delete(long id) {
        try {
            userDao.deleteById(id);
        } catch (Exception e) {
            throw e;
        }

        return true;
    }
}
