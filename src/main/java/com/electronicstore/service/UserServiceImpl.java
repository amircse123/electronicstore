package com.electronicstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicstore.dto.UserDto;
import com.electronicstore.entity.User;
import com.electronicstore.exceptions.ResourceNotFoundException;
import com.electronicstore.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
    UserRepo userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        String id = UUID.randomUUID().toString();
        userDto.setUserId(id);
        User userEntity = modelMapper.map(userDto, User.class);

        User saved = userRepository.save(userEntity);
        return modelMapper.map(saved, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        String userId = userDto.getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given Id"));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        User saved = userRepository.save(user);


        return modelMapper.map(saved, UserDto.class);
    }

    @Override
    public void deleteUser(String id) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("given id not exit in db"));

        if (user != null) {
            userRepository.deleteById(id);
        }


    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> all = userRepository.findAll();
        List<UserDto> userDto = new ArrayList<>();

        for (User u : all) {
            UserDto dto = modelMapper.map(u, UserDto.class);
            userDto.add(dto);
        }
        return userDto;
    }

    @Override
    public UserDto getUserById(String id) {

        User byId = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found !!"));

        return modelMapper.map(byId, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user corresponding to email is not available"));
        UserDto dto = modelMapper.map(user, UserDto.class);

        return dto;
    }

    @Override
    public List<UserDto> serchUserByName(String name) {

        List<User> userList = userRepository.findByNameContaining(name).orElseThrow(() -> new ResourceNotFoundException("user containing this keyword is not available"));
        List<UserDto> collect = userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());


        return collect;
    }

}
