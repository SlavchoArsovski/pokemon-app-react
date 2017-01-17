package com.pokemonapp.servicelayer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.pokemonapp.db.datamodel.Pokemon;
import com.pokemonapp.db.repository.PokemonRepository;
import com.pokemonapp.servicelayer.dto.PokemonDto;
import com.pokemonapp.servicelayer.mapper.PokemonMapper;

/**
 * Created by sarsovsk on 15.01.2017.
 * <p>
 * Implementation of {@link PokemonService}.
 */
@Service
public class PokemonServiceImpl implements PokemonService {

  @Autowired
  private PokemonRepository pokemonRepository;

  public List<PokemonDto> getAllPokemons() {

    List<PokemonDto> pokemons = Lists.newArrayList();

    pokemons =
        pokemonRepository.findAll()
            .stream()
            .map(PokemonMapper::mapPokemonDbModelToDto)
            .collect(Collectors.toList());

    return pokemons;
  }

  public List<PokemonDto> getAllPokemonsByColor(String color) {

    List<PokemonDto> pokemons = Lists.newArrayList();

    pokemons =
        pokemonRepository.findByColor(color)
            .stream()
            .map(PokemonMapper::mapPokemonDbModelToDto)
            .collect(Collectors.toList());

    return pokemons;
  }

  public PokemonDto savePokemon(PokemonDto pokemonDto) {

    Pokemon pokemon = PokemonMapper.mapPokemonDtoToDbModel(pokemonDto);
    Pokemon savedPokemon = pokemonRepository.save(pokemon);

    return PokemonMapper.mapPokemonDbModelToDto(savedPokemon);
  }

  public Optional<PokemonDto> findById(Long pokemonId) {

    Optional<Pokemon> pokemonById = pokemonRepository.findById(pokemonId);

    if (pokemonById.isPresent()) {
      return Optional.of(PokemonMapper.mapPokemonDbModelToDto(pokemonById.get()));
    }

    return Optional.empty();
  }

  public void delete(Long pokemonId) {
    pokemonRepository.delete(pokemonId);
  }
}
