package com.mrc_intern_management.api.service;

import com.mrc_intern_management.api.model.UserModel;
import com.mrc_intern_management.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel add(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public List<UserModel> all() {
        return userRepository.findAll();
    }

    @Override
    public UserModel getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void update(Long userId, UserModel userModelUpdate) {
        UserModel userModelFromDb = userRepository.findById(userId).orElse(null);
        if (userModelFromDb != null) {
            updateUserData(userModelFromDb, userModelUpdate);
        }
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }


    private void updateUserData(UserModel userModelFromDb, UserModel userModelUpdate) {
        userModelFromDb.setName(userModelUpdate.getName());
        userModelFromDb.setGender(userModelUpdate.getGender());
        userModelFromDb.setEmail(userModelUpdate.getEmail());
        userModelFromDb.setPhone(userModelUpdate.getPhone());
        userModelFromDb.setRole(userModelUpdate.getRole());
        userModelFromDb.setAddress(userModelUpdate.getAddress());
        userModelFromDb.setDepartment(userModelUpdate.getDepartment());
        userModelFromDb.setInternId(userModelUpdate.getInternId());
        userModelFromDb.setOrgId(userModelUpdate.getOrgId());
        userRepository.save(userModelFromDb);
    }

}
