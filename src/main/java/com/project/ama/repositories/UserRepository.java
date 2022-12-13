package com.project.ama.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ama.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
