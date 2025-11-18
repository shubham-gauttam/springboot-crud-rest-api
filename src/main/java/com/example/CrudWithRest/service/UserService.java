package com.example.CrudWithRest.service;

import com.example.CrudWithRest.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    User createUser(User user);
    User getUser(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
