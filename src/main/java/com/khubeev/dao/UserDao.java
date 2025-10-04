package com.khubeev.dao;

import com.khubeev.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> getAll();

    void save(User user) throws SQLException;

    User getById(Integer id);

    User getByLogin(String login);

    boolean existsByLogin(String login);
}