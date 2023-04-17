package com.exam.exambook.service.impl;

import com.exam.exambook.model.User;
import com.exam.exambook.model.UserRole;
import com.exam.exambook.repository.RoleRepository;
import com.exam.exambook.repository.UserRepository;
import com.exam.exambook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = userRepository.findByUsername(user.getUsername());

        if (local != null) {
            System.out.println("User is already present!");
            throw new Exception("User is already present!");
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = userRepository.save(user);
        }

        return local;
    }
}
