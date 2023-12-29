package com.example.springbootrestapidb.repository;

import com.example.springbootrestapidb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
