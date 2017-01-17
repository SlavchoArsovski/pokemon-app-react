package com.pokemonapp.rest.dto;

import java.util.List;

import com.google.common.collect.Lists;
import com.pokemonapp.servicelayer.dto.PokemonViewDto;

/**
 * View Model for the home page.
 */
public class PokemonRestViewDto {

  private List<PokemonViewDto> pokemons = Lists.newArrayList();

  public PokemonRestViewDto(List<PokemonViewDto> pokemons) {
    this.pokemons = pokemons;
  }

  public List<PokemonViewDto> getPokemons() {
    return pokemons;
  }
}
