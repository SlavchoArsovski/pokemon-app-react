package com.pokemonapp.servicelayer.service;

import java.util.List;
import java.util.Optional;

import com.pokemonapp.servicelayer.dto.PokemonDto;

/**
 * Created by sarsovsk on 17.01.2017.
 *
 * Pokemon Service.
 */
public interface PokemonService {

  /**
   * @return all pokemons related to logged in user.
   */
  List<PokemonDto> getPokemonsForLoggedInUser();

  /**
   * Saves pokemon.
   *
   * @param pokemonDto the pokemon to be saved.
   * @return saved pokemon.
   */
  PokemonDto savePokemon(PokemonDto pokemonDto);

  /**
   * Finds pokemon by its id.
   *
   * @param pokemonId the pokemon id.
   * @return found pokemon.
   */
  Optional<PokemonDto> findById(Long pokemonId);

  /**
   * Deteles pokemon by its id.
   * @param pokemonId the id of the pokemon to be deleted.
   */
  void delete(Long pokemonId);

}
