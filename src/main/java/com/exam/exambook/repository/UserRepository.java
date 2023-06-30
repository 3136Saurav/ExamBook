package com.exam.exambook.repository;

import com.exam.exambook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public  User findByUsername(String username);
}
