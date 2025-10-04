package com.solncev.service.impl;

import com.solncev.dao.UserDao;
import com.solncev.dao.impl.UserDaoImpl;
import com.solncev.dto.UserDto;
import com.solncev.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(
                u -> new UserDto(u.getName(), u.getLogin())
        ).toList();
    }
}
