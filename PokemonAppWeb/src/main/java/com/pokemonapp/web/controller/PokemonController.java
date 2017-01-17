package com.pokemonapp.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonapp.servicelayer.dto.PokemonDto;
import com.pokemonapp.servicelayer.service.PokemonService;
import com.pokemonapp.web.viewmodel.PokemonViewModel;

/**
 * Created by sarsovsk on 16.01.2017.
 *
 * Rest controller containing CRUD operations for pokemons.
 */
@RestController
public class PokemonController {

  @Autowired
  private PokemonService pokemonService;

  @RequestMapping(
      value = "/pokemonList",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  public PokemonViewModel getPokemonList() {

    List<PokemonDto> allPokemons = pokemonService.getPokemonsForGivenUser();

    PokemonViewModel viewModel = new PokemonViewModel();
    viewModel.setPokemons(allPokemons);

    return viewModel;
  }

  @RequestMapping(
      value = "/pokemonListByColor",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  public List<PokemonDto> getPokemonListByColor(String color) {
    return pokemonService.getAllPokemonsByColor(color);
  }

  @RequestMapping(
      value = "/addPokemon",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST)
  public ResponseEntity<PokemonDto> addPokemon(@RequestBody PokemonDto pokemon) {
    PokemonDto pokemonAdded = pokemonService.savePokemon(pokemon);
    return new ResponseEntity<PokemonDto>(pokemonAdded, HttpStatus.CREATED);
  }

  @RequestMapping(
      value = "/updatePokemon",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.PUT)
  public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemon) {
    PokemonDto pokemonUpdated = pokemonService.savePokemon(pokemon);
    return new ResponseEntity<PokemonDto>(pokemonUpdated, HttpStatus.OK);
  }

  @RequestMapping(
      value = "/deletePokemon/{pokemonId}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.DELETE)
  public ResponseEntity<PokemonDto> deletePokemon(@PathVariable Long pokemonId) {

    Optional<PokemonDto> pokemonFound = pokemonService.findById(pokemonId);

    if (pokemonFound.isPresent()) {
      pokemonService.delete(pokemonId);
      return new ResponseEntity<PokemonDto>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<PokemonDto>(HttpStatus.NOT_FOUND);
  }

}
