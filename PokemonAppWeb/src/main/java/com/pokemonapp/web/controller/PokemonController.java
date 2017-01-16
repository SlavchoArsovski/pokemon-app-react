package com.pokemonapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

  @RequestMapping(
      value = "/pokemonList",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  public List<Pokemon> getPokemonList() {
    return pokemonRepository.findAll();
  }

  @RequestMapping(
      value = "/pokemonListByColor",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  public List<Pokemon> getPokemonListByColor(String color) {
    return pokemonRepository.findByColor(color);
  }

  @RequestMapping(
      value = "/addPokemon",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST)
  public ResponseEntity<Void> addPokemon(@RequestBody Pokemon pokemon) {
    pokemonRepository.save(pokemon);
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }

  @RequestMapping(
      value = "/updatePokemon",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.PUT)
  public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon) {
    pokemonRepository.save(pokemon);
    return new ResponseEntity<Pokemon>(HttpStatus.OK);
  }

  @RequestMapping(
      value = "/deletePokemon/{pokemonId}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.DELETE)
  public ResponseEntity<Pokemon> deletePokemon(@PathVariable Long pokemonId) {
    pokemonRepository.delete(pokemonId);
    return new ResponseEntity<Pokemon>(HttpStatus.NO_CONTENT);
  }

}
