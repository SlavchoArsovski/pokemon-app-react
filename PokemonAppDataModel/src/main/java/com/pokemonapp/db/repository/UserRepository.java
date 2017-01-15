package com.pokemonapp.db.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pokemonapp.db.datamodel.User;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * User Repository.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Find user by his name.
     *
     * @param userName the user name.
     * @return found user if present.
     */
    Optional<User> findByUserName(String userName);
}
