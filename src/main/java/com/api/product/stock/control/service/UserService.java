package com.api.product.stock.control.service;

import com.api.product.stock.control.user.User;
import com.api.product.stock.control.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findByUsername(String username) {
       return (User) userRepository.findByLogin(username);
    }
}