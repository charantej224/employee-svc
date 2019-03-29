package com.employee.service.service;

import com.employee.service.repository.CustomUserDetailsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsImplServiceTest {

    @Mock
    CustomUserDetailsRepository userDetailsRepository;

    @InjectMocks
    UserDetailsImplService userDetailsImplServiceUnderTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        com.employee.service.domain.UserDetails entityUser = new com.employee.service.domain.UserDetails();
        entityUser.setUserName("testUser");
        entityUser.setPassword("testUser");
        when(userDetailsRepository.getByUserName("testUser")).thenReturn(Optional.of(entityUser));
    }

    @Test
    public void testLoadUserByUsername() throws Exception {
        final UserDetails result = userDetailsImplServiceUnderTest.loadUserByUsername("testUser");
        assertEquals("testUser", result.getUsername());
        assertEquals("testUser", result.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsername_unknownUser(){
        userDetailsImplServiceUnderTest.loadUserByUsername("exceptionUser");
    }
}
