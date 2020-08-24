package com.test.demo.service;

import com.test.demo.model.User;
import com.test.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created on 05.08.2020
 *
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String name) {
        return userRepository.findByEmail(name).orElseThrow(IllegalArgumentException::new);
    }
}
