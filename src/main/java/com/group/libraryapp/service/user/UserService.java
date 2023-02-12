package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;

import java.util.List;

public interface UserService {


    void updateUser(UserUpdateRequest userUpdateRequest);
    void deleteUser(String name);

    void saveUser(UserCreateRequest userCreateRequest);

    List<UserResponse> getUsers();



}
