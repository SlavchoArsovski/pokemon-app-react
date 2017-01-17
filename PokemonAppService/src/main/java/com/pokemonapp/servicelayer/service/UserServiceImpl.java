package com.pokemonapp.servicelayer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pokemonapp.db.datamodel.User;
import com.pokemonapp.db.datamodel.UserRole;
import com.pokemonapp.db.repository.UserRepository;
import com.pokemonapp.db.repository.UserRoleRepository;

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

    @Autowired
    @Qualifier("CustomUserDetailService")
    private UserDetailsService userDetailsService;

    @Override
    public void saveNewUser(User user, String role) {

        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);

        userRepository.save(user);

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
