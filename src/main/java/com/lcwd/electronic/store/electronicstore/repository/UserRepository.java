package com.lcwd.electronic.store.electronicstore.repository;

import com.lcwd.electronic.store.electronicstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,String> {


    public Optional<User> findByEmail(String email);

    public Optional<List<User>> findByNameContaining(String keyword) ;




}
