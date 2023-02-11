package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import com.group.libraryapp.service.user.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/user") //Post /User
    public void saveUser(@RequestBody UserCreateRequest userCreateRequest)
    {
        userService.saveUser(userCreateRequest);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }


    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest userUpdateRequest)
    {
            userService.updateUser(userUpdateRequest);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name)
    {
        userService.deleteUser(name);
    }

    @GetMapping("/user/error-test")
    public void errorTest()
    {
        throw new IllegalArgumentException();
    }






}
