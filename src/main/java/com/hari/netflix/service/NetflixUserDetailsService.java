package com.hari.netflix.service;

import com.hari.netflix.pojo.NetflixUserDetails;
import com.hari.netflix.pojo.User;
import com.hari.netflix.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class NetflixUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findUser(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new NetflixUserDetails(user);
    }
}