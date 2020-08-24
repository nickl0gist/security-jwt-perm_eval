package com.test.demo.service;

import com.test.demo.configuration.UserPrincipal;
import com.test.demo.model.User;
import com.test.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * Created on 26.07.2020
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(s).orElse(null);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}
