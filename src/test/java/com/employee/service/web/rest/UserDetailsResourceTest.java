package com.employee.service.web.rest;

import com.employee.service.domain.UserDetails;
import com.employee.service.repository.CustomUserDetailsRepository;
import com.employee.service.request.domain.GenericMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsResourceTest {

    @Mock
    private CustomUserDetailsRepository mockUserDetailsRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    @InjectMocks
    private UserDetailsResource userDetailsResourceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        when(mockBCryptPasswordEncoder.encode("password")).thenReturn("password");
        when(mockUserDetailsRepository.save(any(UserDetails.class))).thenReturn(null);
    }

    @Test
    public void testSignUpUser() {
        UserDetails userDetails = new UserDetails();
        userDetails.setPassword("password");
        userDetails.setUserName("user");
        ResponseEntity<GenericMessage> result = userDetailsResourceUnderTest.signUpUser(userDetails);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("user created",result.getBody().getMessage());
    }
}
