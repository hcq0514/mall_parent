package com.mall.user.controller;

import com.mall.user.entity.UserEntity;
import com.mall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
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

    @PostMapping("login")
    public ResponseEntity<UserEntity> tempLogin() {
        return ResponseEntity.ok(new UserEntity());
    }

    @GetMapping("sendVerifyCode")
    public ResponseEntity<UserEntity> sendVerifyCode() {
        userService.sendVerifyCode("13113051027");
        return null;
    }

    /**
     * 注册
     * @param user
     * @param code
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid UserEntity user, @RequestParam("code") String code) {
        Boolean boo = userService.register(user, code);
        if (boo == null || !boo) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}



