package com.pokemonapp.servicelayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemonapp.db.datamodel.Pokemon;
import com.pokemonapp.db.repository.PokemonRepository;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * Pokemon Service.
 */
@Service
public class PokemonService {

  @Autowired
  private PokemonRepository pokemonRepository;

  public List<Pokemon> getAllPokemons() {
    return pokemonRepository.findAll();
  }
}
