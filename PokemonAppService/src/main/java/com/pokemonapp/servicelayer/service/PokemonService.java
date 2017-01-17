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

  List<PokemonDto> getPokemonsForGivenUser();

  List<PokemonDto> getAllPokemonsByColor(String color);

  PokemonDto savePokemon(PokemonDto pokemonDto);

  Optional<PokemonDto> findById(Long pokemonId);

  void delete(Long pokemonId);

}
