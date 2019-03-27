package com.employee.service.web.rest;

import com.employee.service.config.ApplicationProperties;
import com.employee.service.domain.UserDetails;
import com.employee.service.repository.CustomUserDetailsRepository;
import com.employee.service.request.domain.GenericMessage;
import com.employee.service.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.employee.service.config.Constants.MESSAGE_105;

/**
 * REST controller for managing UserDetails.
 */
@RestController
public class UserDetailsResource {

    private final Logger log = LoggerFactory.getLogger(UserDetailsResource.class);

    private static final String ENTITY_NAME = "employeeServiceUserDetails";

    @Autowired
    private CustomUserDetailsRepository userDetailsRepository;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    HttpHeaders httpHeaders;

    @Autowired
    ApplicationProperties applicationProperties;


    @PostMapping("/login")
    public ResponseEntity<GenericMessage> login(@RequestBody UserDetails userDetails) {
//        GenericMessage<String> genericMessage;
//        Optional<UserDetails> userDetailsEntity = userDetailsRepository.getByUserNameAndPassword(userDetails.getUserName(), userDetails.getPassword());
//        final Authentication authentication = authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(
//                userDetails.getUserName(),
//                userDetails.getPassword()
//            )
//        );
//        if (userDetailsEntity.isPresent()) {
//            genericMessage = new GenericMessage(tokenProvider.createToken(authentication, true), "");
//            return ResponseEntity.ok().headers(httpHeaders).body(genericMessage);
//        } else {
//            genericMessage = new GenericMessage(MESSAGE_105, applicationProperties.getMessages().get(MESSAGE_105));
//            return ResponseEntity.badRequest().headers(httpHeaders).body(genericMessage);
//        }
        return null;

    }

}
