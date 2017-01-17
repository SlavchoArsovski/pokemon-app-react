package com.pokemonapp.servicelayer.service;

import java.util.List;
import java.util.Optional;

import com.pokemonapp.db.datamodel.User;
import com.pokemonapp.db.datamodel.UserRole;

/**
 * Created by sarsovsk on 17.01.2017.
 *
 * User Service.
 */
public interface UserService {

  /**
   * Saves new user with given role.
   *
   * @param user the user.
   * @param role the role of the user.
   */
  void saveNewUser(User user, String role);

  /**
   * Find user by user name.
   *
   * @param userName the user name.
   * @return user found.
   */
  Optional<User> findByUserName(String userName);

  /**
   * Find roles of give user.
   *
   * @param userName the user name.
   * @return list of roles.
   */
  List<UserRole> findUserRoles(String userName);

}