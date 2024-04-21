package com.lcwd.electronic.store.electronicstore.service;

import com.lcwd.electronic.store.electronicstore.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUser(String id);

    List<UserDto> getAllUser();

    UserDto getUserById(String id);

    UserDto getUserByEmail(String email);


    List<UserDto> serchUserByName(String name);

}
