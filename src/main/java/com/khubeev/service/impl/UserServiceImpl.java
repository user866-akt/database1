package com.khubeev.service.impl;

import com.khubeev.dao.UserDao;
import com.khubeev.dao.impl.UserDaoImpl;
import com.khubeev.dto.UserDto;
import com.khubeev.entity.User;
import com.khubeev.service.UserService;
import com.khubeev.util.PasswordUtil;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(
                u -> new UserDto(u.getName(), u.getLogin())
        ).toList();
    }

    public void registerUser(User user) throws SQLException {
        String hashedPassword = PasswordUtil.encrypt(user.getPassword());
        user.setPassword(hashedPassword);

        if (userDao.existsByLogin(user.getLogin())) {
            throw new SQLException("User with login '" + user.getLogin() + "' already exists");
        }

        userDao.save(user);
    }

    public boolean authenticate(String login, String password) {
        User user = userDao.getByLogin(login);
        if (user == null) {
            return false;
        }

        String hashedPassword = PasswordUtil.encrypt(password);
        return hashedPassword.equals(user.getPassword());
    }

    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }
}