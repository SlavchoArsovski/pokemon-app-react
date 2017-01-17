package com.pokemonapp.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pokemonapp.db.datamodel.Pokemon;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * Pokemon Repository.
 */
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {

  /**
   * @return list of all pokemons.
   */
  List<Pokemon> findAll();

  /**
   * Retrieve pokemons for given user.
   *
   * @return list of found pokemons.
   */
  List<Pokemon> findByUserName(String userName);

  /**
   * Retrieve pokemons filtered by color.
   *
   * @param color the pokemon color.
   * @return list of found pokemons.
   */
  List<Pokemon> findByColor(String color);

  /**
   * Retrieve pokemon by its id.
   *
   * @param id the pokemon id.
   * @return found pokemon if present.
   */
  Optional<Pokemon> findById(Long id);

}
