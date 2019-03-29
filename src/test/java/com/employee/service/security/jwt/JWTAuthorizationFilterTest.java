package com.employee.service.security.jwt;

import org.junit.Before;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;

import static org.mockito.MockitoAnnotations.initMocks;

public class JWTAuthorizationFilterTest {

    @Mock
    private AuthenticationManager mockAuthManager;

    private JWTAuthorizationFilter jwtAuthorizationFilterUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        jwtAuthorizationFilterUnderTest = new JWTAuthorizationFilter(mockAuthManager);
    }
}
