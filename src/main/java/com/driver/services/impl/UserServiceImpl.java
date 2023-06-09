package com.driver.services.impl;

import com.driver.model.User;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository4;
    @Override
    public void deleteUser(Integer userId) {
        userRepository4.delete(userRepository4.findById(userId).get());
    }

    @Override
    public User updatePassword(Integer userId, String password) {
        User update = userRepository4.findById(userId).get();
        update.setPassword(password);

        return update;
    }

    @Override
    public void register(String name, String phoneNumber, String password) {
        User newUser = new User();

        newUser.setName(name);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setPassword(password);

        userRepository4.save(newUser);
    }
}
