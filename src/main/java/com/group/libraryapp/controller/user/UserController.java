package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserService;
import com.group.libraryapp.service.user.UserServiceV1;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    @Qualifier("main")
    private final UserService userServiceV1;

    public UserController(UserServiceV1 userServiceV1){
        this.userServiceV1 = userServiceV1;
    }


    @PostMapping("/user") //Post /User
    public void saveUser(@RequestBody UserCreateRequest userCreateRequest)
    {
        userServiceV1.saveUser(userCreateRequest);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userServiceV1.getUsers();
    }


    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest userUpdateRequest)
    {
            userServiceV1.updateUser(userUpdateRequest);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name)
    {
        userServiceV1.deleteUser(name);
    }

    @GetMapping("/user/error-test")
    public void errorTest()
    {
        throw new IllegalArgumentException();
    }






}
