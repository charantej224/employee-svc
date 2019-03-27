package com.employee.service.repository;

import com.employee.service.domain.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomUserDetailsRepository extends UserDetailsRepository{

    Optional<UserDetails> getByUserNameAndPassword(String userName,String password);
}
