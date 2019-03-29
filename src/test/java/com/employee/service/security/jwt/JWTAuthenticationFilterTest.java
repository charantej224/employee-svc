package com.employee.service.security.jwt;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class JWTAuthenticationFilterTest {

    @Mock
    private AuthenticationManager mockAuthenticationManager;

    private JWTAuthenticationFilter jwtAuthenticationFilterUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        jwtAuthenticationFilterUnderTest = new JWTAuthenticationFilter(mockAuthenticationManager);
    }

    @Test
    public void testAttemptAuthentication() throws Exception {
        // Setup
        final HttpServletRequest req = null;
        final HttpServletResponse res = null;
        final Authentication expectedResult = null;

        // Run the test
        final Authentication result = jwtAuthenticationFilterUnderTest.attemptAuthentication(req, res);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
