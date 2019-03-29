package com.employee.service.web.rest;

import com.employee.service.domain.UserDetails;
import com.employee.service.repository.CustomUserDetailsRepository;
import com.employee.service.request.domain.GenericMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

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
    }

    @Test
    public void testSignUpUser() {
        // Setup
        final UserDetails userDetails = null;
        final ResponseEntity<GenericMessage> expectedResult = null;

        // Run the test
        final ResponseEntity<GenericMessage> result = userDetailsResourceUnderTest.signUpUser(userDetails);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
