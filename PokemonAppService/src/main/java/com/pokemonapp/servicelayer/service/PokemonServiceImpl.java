package com.pokemonapp.servicelayer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

  public List<PokemonDto> getPokemonsForGivenUser() {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = auth.getName();

    List<PokemonDto> pokemons =
        pokemonRepository.findByUserName(name)
            .stream()
            .map(PokemonMapper::mapPokemonDbModelToDto)
            .collect(Collectors.toList());

    return pokemons;
  }

  public List<PokemonDto> getAllPokemonsByColor(String color) {

    List<PokemonDto> pokemons =
        pokemonRepository.findByColor(color)
            .stream()
            .map(PokemonMapper::mapPokemonDbModelToDto)
            .collect(Collectors.toList());

    return pokemons;
  }

  public PokemonDto savePokemon(PokemonDto pokemonDto) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = auth.getName();

    Pokemon pokemon = PokemonMapper.mapPokemonDtoToDbModel(pokemonDto);
    pokemon.setUserName(name);

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
