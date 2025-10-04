package com.khubeev.service;

import com.khubeev.dto.UserDto;
import com.khubeev.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    void registerUser(User user) throws SQLException;

    boolean authenticate(String login, String password);

    User getByLogin(String login);
}