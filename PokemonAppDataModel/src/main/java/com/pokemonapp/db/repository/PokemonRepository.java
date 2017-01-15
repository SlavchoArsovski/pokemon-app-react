package com.pokemonapp.db.repository;

import java.util.List;

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

}
