package com.pokemonapp.db.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pokemonapp.db.datamodel.UserRole;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * User Role Repository.
 */
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    /**
     * Find user roles.
     *
     * @param userName the user name.
     * @return list of user roles.
     */
    List<UserRole> findByUserName(String userName);
}
