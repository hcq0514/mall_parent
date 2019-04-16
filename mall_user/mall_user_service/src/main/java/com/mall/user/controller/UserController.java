package com.mall.user.controller;

import com.mall.user.entity.UserEntity;
import com.mall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.spec.EncodedKeySpec;

@RestController("user")
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    public ResponseEntity<UserEntity> queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        UserEntity user = userService.queryUser(username, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(user);
    }
}



