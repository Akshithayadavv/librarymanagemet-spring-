package com.libraryPro.service;

import com.libraryPro.config.UsersUserDetails;
import com.libraryPro.entity.Users;
import com.libraryPro.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsersUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;



      @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByName(username);


        System.out.println(username);
        if (users == null) {

            throw new UsernameNotFoundException("User not found");
        }
        System.out.println(users);
        return new UsersUserDetails(users);
    }
}