package com.pokemonapp.rest.controller;

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

import com.pokemonapp.rest.dto.PokemonRestViewDto;
import com.pokemonapp.servicelayer.dto.PokemonViewDto;
import com.pokemonapp.servicelayer.service.PokemonService;

/**
 * Created by sarsovsk on 16.01.2017.
 *
 * Rest controller containing CRUD operations for pokemons.
 */
@RestController
public class PokemonRestController {

  @Autowired
  private PokemonService pokemonService;

  /**
   * Returns pokemon list for the logged in user.
   *
   * @return model containing the list of pokemons.
   */
  @RequestMapping(
      value = "/pokemonList",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  public PokemonRestViewDto getPokemonList() {

    List<PokemonViewDto> pokemons = pokemonService.getAllPokemons();

    PokemonRestViewDto restViewDto = new PokemonRestViewDto(pokemons);

    return restViewDto;
  }

  /**
   * Adds new pokemon for the logged in user.
   * @param pokemon the pokemon detail.
   *
   * @return added pokemon detail.
   */
  @RequestMapping(
      value = "/addPokemon",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST)
  public ResponseEntity<PokemonViewDto> addPokemon(@RequestBody PokemonViewDto pokemon) {

    PokemonViewDto pokemonAdded = pokemonService.savePokemon(pokemon);

    return new ResponseEntity<PokemonViewDto>(pokemonAdded, HttpStatus.CREATED);
  }


  /**
   * Updates existing pokemon which belongs to the logged in user.
   *
   * TODO: should be checked if the pokemon belongs to the given user.
   * @param pokemon the pokemon detail updated.
   *
   * @return added pokemon.
   */
  @RequestMapping(
      value = "/updatePokemon",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.PUT)
  public ResponseEntity<PokemonViewDto> updatePokemon(@RequestBody PokemonViewDto pokemon) {

    PokemonViewDto pokemonUpdated = pokemonService.savePokemon(pokemon);

    return new ResponseEntity<PokemonViewDto>(pokemonUpdated, HttpStatus.OK);
  }

  /**
   * Deletes pokemon for given id.
   *
   * TODO: should be checked if the pokemon belongs to the given user.
   * @param pokemonId the pokemon id.
   * @return the deleted pokemon.
   */
  @RequestMapping(
      value = "/deletePokemon/{pokemonId}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.DELETE)
  public ResponseEntity<PokemonViewDto> deletePokemon(@PathVariable Long pokemonId) {

    Optional<PokemonViewDto> pokemonFound = pokemonService.findById(pokemonId);

    if (pokemonFound.isPresent()) {
      pokemonService.delete(pokemonId);
      return new ResponseEntity<PokemonViewDto>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<PokemonViewDto>(HttpStatus.NOT_FOUND);
  }

}
