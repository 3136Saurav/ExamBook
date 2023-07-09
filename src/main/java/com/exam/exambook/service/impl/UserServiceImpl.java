package com.exam.exambook.service.impl;

import com.exam.exambook.exception.UserAlreadyExistsException;
import com.exam.exambook.model.User;
import com.exam.exambook.model.UserRole;
import com.exam.exambook.repository.RoleRepository;
import com.exam.exambook.repository.UserRepository;
import com.exam.exambook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    final String profileImageUrl = "https://i.pravatar.cc/300?u=";

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new Exception("Username cannot be blank!");
        }
        User local = userRepository.findByUsername(user.getUsername());

        if (local != null) {
            System.out.println("User is already present!");
            throw new UserAlreadyExistsException("User is already present!");
        } else {
            user.setProfile(profileImageUrl + user.getFirstName());
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = userRepository.save(user);
        }

        return local;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
