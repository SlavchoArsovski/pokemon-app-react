package com.pokemonapp.servicelayer.service;

import java.util.List;
import java.util.Optional;

import com.pokemonapp.servicelayer.dto.PokemonViewDto;

/**
 * Created by sarsovsk on 17.01.2017.
 *
 * Pokemon Service.
 */
public interface PokemonService {

  /**
   * @return all pokemons related to logged in user.
   */
  List<PokemonViewDto> getPokemonsForLoggedInUser();

  List<PokemonViewDto> getAllPokemons();

  /**
   * Saves pokemon.
   *
   * @param pokemonViewDto the pokemon to be saved.
   * @return saved pokemon.
   */
  PokemonViewDto savePokemon(PokemonViewDto pokemonViewDto);

  /**
   * Finds pokemon by its id.
   *
   * @param pokemonId the pokemon id.
   * @return found pokemon.
   */
  Optional<PokemonViewDto> findById(Long pokemonId);

  /**
   * Deteles pokemon by its id.
   * @param pokemonId the id of the pokemon to be deleted.
   */
  void delete(Long pokemonId);

}
