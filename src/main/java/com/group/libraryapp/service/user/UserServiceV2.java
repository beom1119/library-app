package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


//@Primary
@Qualifier("main")
@Service
public class UserServiceV2 {


    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest request)
    {
       User saveUser =  userRepository.save(new User(request.getName(),request.getAge()));
        System.out.printf(saveUser.toString());
    }

    @Transactional
    public List<UserResponse> getUsers()
    {
      return userRepository.findAll().stream().map(UserResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public User updateUser(UserUpdateRequest userUpdateRequest)
    {
        //select from user where id =?;
        User updateUser = userRepository.findById(userUpdateRequest.getId()).orElseThrow(IllegalArgumentException::new);
        updateUser.updateName(userUpdateRequest.getName());
        userRepository.save(updateUser);
        return updateUser;
    }


    @Transactional
    public void deleteUser(String name)
    {
        User deleteUser = userRepository.findByName(name);
        if (deleteUser == null)
        {
            throw new IllegalArgumentException();
        }
        userRepository.delete(deleteUser);

//        if (!userRepository.existsByName(name))
//        {
//            throw new IllegalArgumentException();
//        }

//        User deleteUser = userRepository.findByName(name);
//        userRepository.delete(deleteUser);
    }



}
