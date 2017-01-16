package com.pokemonapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonapp.db.datamodel.Pokemon;
import com.pokemonapp.db.repository.PokemonRepository;

/**
 * Created by sarsovsk on 16.01.2017.
 */
@RestController
public class PokemonController {

  @Autowired
  private PokemonRepository pokemonRepository;

  @RequestMapping(value = "/pokemon-list", method = RequestMethod.GET)
  public List<Pokemon> getPokemonList() {
    return pokemonRepository.findAll();
  }

  @RequestMapping(value = "/pokemon-list-by-color", method = RequestMethod.GET)
  public List<Pokemon> getPokemonListByColor(String color) {
    return pokemonRepository.findByColor(color);
  }

  @RequestMapping(value = "/addPokemon", method = RequestMethod.POST)
  public void addPokemon(Pokemon pokemon) {
    pokemonRepository.save(pokemon);
  }

  @RequestMapping(value = "/addPokemon", method = RequestMethod.PUT)
  public void updatePokemon(Pokemon pokemon) {
    pokemonRepository.save(pokemon);
  }

  @RequestMapping(value = "/deletePokemon", method = RequestMethod.DELETE)
  public void deletePokemon(Long pokemonId) {
    pokemonRepository.delete(pokemonId);
  }

}
