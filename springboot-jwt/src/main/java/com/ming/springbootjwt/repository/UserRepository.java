package com.ming.springbootjwt.repository;

import com.ming.springbootjwt.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
