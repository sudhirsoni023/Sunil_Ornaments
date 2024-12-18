package com.ornaments.sunil.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ornaments.sunil.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}
