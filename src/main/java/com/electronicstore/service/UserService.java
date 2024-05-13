package com.electronicstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.electronicstore.dto.UserDto;

@Service
public interface UserService {

	UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUser(String id);

    List<UserDto> getAllUser();

    UserDto getUserById(String id);

    UserDto getUserByEmail(String email);


    List<UserDto> serchUserByName(String name);

}
