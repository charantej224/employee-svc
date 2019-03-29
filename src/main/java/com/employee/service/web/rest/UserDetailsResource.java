package com.employee.service.web.rest;

import com.employee.service.domain.UserDetails;
import com.employee.service.repository.CustomUserDetailsRepository;
import com.employee.service.request.domain.GenericMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailsResource {

    @Autowired
    private CustomUserDetailsRepository userDetailsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/sign-up")
    public ResponseEntity<GenericMessage> signUpUser(@RequestBody UserDetails userDetails){
        GenericMessage<String> genericMessage;
        userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        userDetailsRepository.save(userDetails);
        genericMessage = new GenericMessage<>("user created","");
        return ResponseEntity.ok().body(genericMessage);
    }
}
