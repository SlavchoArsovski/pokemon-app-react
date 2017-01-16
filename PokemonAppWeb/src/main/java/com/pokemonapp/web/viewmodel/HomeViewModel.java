package com.pokemonapp.web.viewmodel;

import java.util.List;

import com.pokemonapp.db.datamodel.Pokemon;

/**
 * View Model for the home page.
 */
public class HomeViewModel {

  private List<Pokemon> pokemons;

  public List<Pokemon> getPokemons() {
    return pokemons;
  }

  public void setPokemons(List<Pokemon> pokemons) {
    this.pokemons = pokemons;
  }
}
