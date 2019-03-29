package com.employee.service.service;

import com.employee.service.repository.CustomUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsImplService implements UserDetailsService {

    @Autowired
    private CustomUserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.employee.service.domain.UserDetails> userDetailsEntity =  userDetailsRepository.getByUserName(username);
        if(!userDetailsEntity.isPresent())
            throw new UsernameNotFoundException(username);
        return new User(userDetailsEntity.get().getUserName(),userDetailsEntity.get().getPassword(),emptyList());
    }
}
