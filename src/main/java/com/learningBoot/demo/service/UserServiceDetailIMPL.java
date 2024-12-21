package com.learningBoot.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.learningBoot.demo.entity.UserEntry;
import com.learningBoot.demo.repository.UserEntryRepository;

@Component
public class UserServiceDetailIMPL implements UserDetailsService {
    @Autowired
    private UserEntryRepository userEntryRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
   
            Optional<UserEntry> user=userEntryRepository.findByuserName(userName);
            if (user.isPresent()) {
                UserDetails userDetails= org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getUserName())
                .password(user.get().getPasswd())
                .roles(user.get().getRoles().toArray(new String[0])).build();
                System.out.println("My UserEntry Found in This Security is::::"+userDetails);
                return userDetails;
            }
            throw new UsernameNotFoundException("User Not Found in DataBase"+userName);
        
        
    }
}
