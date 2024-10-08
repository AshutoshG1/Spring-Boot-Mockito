package com.codewithashutosh.spring.mockito.repository;

import com.codewithashutosh.spring.mockito.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByAddress(String address);
}
