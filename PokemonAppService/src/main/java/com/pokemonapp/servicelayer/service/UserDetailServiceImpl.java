package com.pokemonapp.servicelayer.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pokemonapp.db.datamodel.User;
import com.pokemonapp.db.datamodel.UserRole;

/**
 * Created by sarsovsk on 17.01.2017.
 *
 * Implementation of {@link UserDetailsService}.
 */
@Service("CustomUserDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    try {
      Optional<User> userOptional = userService.findByUserName(username);
      if (userOptional.isPresent()) {
        User user = userOptional.get();
        LOGGER.debug(" user from username " + user.getUserName());

        List<UserRole> userRoles = userService.findUserRoles(username);
        return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
            user.getPassword(),
            getAuthorities(userRoles));
      }

      LOGGER.debug("user not found with the provided username");
      return null;
    } catch (Exception e) {
      throw new UsernameNotFoundException("User not found");
    }
  }

  private Set<GrantedAuthority> getAuthorities(List<UserRole> userRoles) {
    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
    for (UserRole role : userRoles) {
      GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
      authorities.add(grantedAuthority);
    }
    LOGGER.debug("user authorities are " + authorities.toString());
    return authorities;
  }

}
