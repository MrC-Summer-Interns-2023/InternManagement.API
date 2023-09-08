package com.mrc_intern_management.api.controller;

import com.mrc_intern_management.api.model.UserRole;
import com.mrc_intern_management.api.model.UserModel;
import com.mrc_intern_management.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
public class UserController {

@Autowired
private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> userModels = userService.all();
        return new ResponseEntity<>(userModels, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> get(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody UserModel userModel) {
        UserModel addedUserModel = userService.add(userModel);

        HttpHeaders httpHeaders = new HttpHeaders();

        if (addedUserModel.getRole() == UserRole.INTERN) {
            httpHeaders.add("Location", "/api/v1/user/intern/" + addedUserModel.getInternId());
        } else if (addedUserModel.getRole() == UserRole.ORGANIZER) {
            httpHeaders.add("Location", "/api/v1/user/org/" + addedUserModel.getOrgId());
        }

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(addedUserModel.getRole().toString());
    }

    @PutMapping("update/{userId}")
    public ResponseEntity<UserModel> update(@PathVariable Long userId, @RequestBody UserModel userModel) {
        UserModel updatedUserModel = userService.getUserById(userId);
        if (updatedUserModel != null ) {
            userService.update(userId, userModel);
            return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<UserModel> delete(@PathVariable Long userId) {
        UserModel deletedUserModel = userService.getUserById(userId);
        if (deletedUserModel != null) {
            userService.delete(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
