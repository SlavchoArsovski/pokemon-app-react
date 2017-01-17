package com.pokemonapp.servicelayer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pokemonapp.db.datamodel.User;
import com.pokemonapp.db.datamodel.UserRole;
import com.pokemonapp.db.repository.UserRepository;
import com.pokemonapp.db.repository.UserRoleRepository;
import com.pokemonapp.servicelayer.service.UserService;

/**
 * Created by sarsovsk on 17.01.2017.
 *
 * Implementation of {@link UserService}.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void saveNewUser(String userName, String userPassword, String role) {

        String passwordEncoded = passwordEncoder.encode(userPassword);

        // add user
        User user = new User();
        user.setUserName(userName);
        user.setPassword(passwordEncoded);
        user.setEnabled(true);

        userRepository.save(user);

        // add user roles
        UserRole userRole = new UserRole();
        userRole.setUserName(user.getUserName());
        userRole.setRole(role);

        userRoleRepository.save(userRole);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<UserRole> findUserRoles(String userName) {
        return userRoleRepository.findByUserName(userName);
    }
}
