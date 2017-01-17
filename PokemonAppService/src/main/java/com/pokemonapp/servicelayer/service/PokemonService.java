package com.pokemonapp.servicelayer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.pokemonapp.db.datamodel.Pokemon;
import com.pokemonapp.servicelayer.dto.PokemonDto;
import com.pokemonapp.servicelayer.mapper.PokemonMapper;

/**
 * Created by sarsovsk on 17.01.2017.
 *
 * Pokemon Service
 */
public interface PokemonService {

  public List<PokemonDto> getAllPokemons();

  List<PokemonDto> getAllPokemonsByColor(String color);

  PokemonDto savePokemon(PokemonDto pokemonDto);

  Optional<PokemonDto> findById(Long pokemonId);

  void delete(Long pokemonId);

}
