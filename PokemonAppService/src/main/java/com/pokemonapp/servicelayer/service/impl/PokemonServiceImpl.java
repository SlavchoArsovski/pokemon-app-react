package com.pokemonapp.servicelayer.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pokemonapp.db.datamodel.Pokemon;
import com.pokemonapp.db.repository.PokemonRepository;
import com.pokemonapp.servicelayer.dto.PokemonViewDto;
import com.pokemonapp.servicelayer.mapper.PokemonMapper;
import com.pokemonapp.servicelayer.service.PokemonService;

/**
 * Created by sarsovsk on 15.01.2017.
 * <p>
 * Implementation of {@link PokemonService}.
 */
@Service
public class PokemonServiceImpl implements PokemonService {

  @Autowired
  private PokemonRepository pokemonRepository;

  public List<PokemonViewDto> getPokemonsForLoggedInUser() {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = auth.getName();

    List<PokemonViewDto> pokemons =
        pokemonRepository.findByUserName(name)
            .stream()
            .map(PokemonMapper::mapPokemonDbModelToDto)
            .collect(Collectors.toList());

    return pokemons;
  }

  public List<PokemonViewDto> getAllPokemons() {

    List<PokemonViewDto> pokemons =
        pokemonRepository.findAll()
            .stream()
            .map(PokemonMapper::mapPokemonDbModelToDto)
            .collect(Collectors.toList());

    return pokemons;
  }

  public PokemonViewDto savePokemon(PokemonViewDto pokemonViewDto) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = auth.getName();

    Pokemon pokemon = PokemonMapper.mapPokemonDtoToDbModel(pokemonViewDto);
    pokemon.setUserName(name);

    Pokemon savedPokemon = pokemonRepository.save(pokemon);

    return PokemonMapper.mapPokemonDbModelToDto(savedPokemon);
  }

  public Optional<PokemonViewDto> findById(Long pokemonId) {

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
