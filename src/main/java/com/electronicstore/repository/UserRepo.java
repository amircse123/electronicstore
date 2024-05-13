package com.electronicstore.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.electronicstore.entity.User;


public interface UserRepo extends JpaRepository<User, String> {

	public Optional<User> findByEmail(String email);

	public Optional<List<User>> findByNameContaining(String keyword);
}
