package com.exam.exambook.service;

import com.exam.exambook.model.User;
import com.exam.exambook.model.UserRole;

import java.util.Set;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
}
