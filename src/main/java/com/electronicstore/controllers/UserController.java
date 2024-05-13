package com.electronicstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicstore.dto.UserDto;
import com.electronicstore.service.UserService;
import com.electronicstore.utility.ApiResponseMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	  //create user
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        UserDto user = userService.createUser(userDto);

        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    //update user
    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        UserDto userDto1 = userService.updateUser(userDto);

        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }

    //delete user
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("userId") String id) {
        userService.deleteUser(id);
        ApiResponseMessage message = ApiResponseMessage
                .builder()
                .message("User deleted successfully !!..")
                .status(HttpStatus.OK)
                .success(true)
                .build();


        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //get all user
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {

        return new ResponseEntity<List<UserDto>>(userService.getAllUser(), HttpStatus.OK);
    }

    //get user by id
    @GetMapping("id/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String id) {
        UserDto userById = userService.getUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    //get by email
    @GetMapping("email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String emailId) {

        return new ResponseEntity<>(userService.getUserByEmail(emailId), HttpStatus.OK);
    }

    //search user
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable("keyword") String key) {

        return new ResponseEntity<>(userService.serchUserByName(key), HttpStatus.OK);
    }

}
