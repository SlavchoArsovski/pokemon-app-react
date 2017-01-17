package com.pokemonapp.web.viewmodel;

import java.util.List;

import com.pokemonapp.servicelayer.dto.PokemonViewDto;

/**
 * View Model for the home page.
 */
public class PokemonViewModel {

  private List<PokemonViewDto> pokemons;

  public List<PokemonViewDto> getPokemons() {
    return pokemons;
  }

  public void setPokemons(List<PokemonViewDto> pokemons) {
    this.pokemons = pokemons;
  }
}
