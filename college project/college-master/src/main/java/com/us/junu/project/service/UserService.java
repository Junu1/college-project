package com.us.junu.project.service;


import com.us.junu.project.model.User;

public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);
}
