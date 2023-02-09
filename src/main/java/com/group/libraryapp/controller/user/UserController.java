package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {


    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user") //Post /User
    public void saveUser(@RequestBody UserCreateRequest userCreateRequest)
    {
        String sql = "insert into users (name, age) values (?,?)";
        jdbcTemplate.update(sql,userCreateRequest.getName(),userCreateRequest.getAge());
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        String sql = "Select * from users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }


    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest userUpdateRequest)
    {
        String readSql = "Select * From users where id = ? ";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, userUpdateRequest.getId()).isEmpty();
        if (isUserNotExist)
        {
            throw new IllegalArgumentException();
        }

        String sql = "update users set name = ? where id = ? ";
        jdbcTemplate.update(sql,userUpdateRequest.getName(), userUpdateRequest.getId());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name)
    {
        String readSql = "Select * From users where name = ? ";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0,name).isEmpty();
        if (isUserNotExist)
        {
            throw new IllegalArgumentException();
        }

        String sql ="delete from users where name = ? ";
        jdbcTemplate.update(sql, name);
    }

    @GetMapping("/user/error-test")
    public void errorTest()
    {
        throw new IllegalArgumentException();
    }






}
