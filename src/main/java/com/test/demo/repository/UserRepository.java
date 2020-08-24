package com.test.demo.repository;

import com.test.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created on 26.07.2020
 *
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
