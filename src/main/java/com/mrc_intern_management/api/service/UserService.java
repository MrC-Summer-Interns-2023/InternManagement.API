package com.mrc_intern_management.api.service;

import com.mrc_intern_management.api.model.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> all();
    UserModel add(UserModel userModel);
    UserModel getUserById(Long userId);

    void update(Long userId, UserModel userModel);

    void delete(Long userId);
}
