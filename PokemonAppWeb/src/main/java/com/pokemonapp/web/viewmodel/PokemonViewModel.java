package com.pokemonapp.web.viewmodel;

import java.util.List;

import com.pokemonapp.servicelayer.dto.PokemonDto;

/**
 * View Model for the home page.
 */
public class PokemonViewModel {

  private List<PokemonDto> pokemons;

  public List<PokemonDto> getPokemons() {
    return pokemons;
  }

  public void setPokemons(List<PokemonDto> pokemons) {
    this.pokemons = pokemons;
  }
}
