package com.pokemonapp.servicelayer.mapper;

import org.apache.commons.lang3.StringUtils;

import com.pokemonapp.db.datamodel.Pokemon;
import com.pokemonapp.servicelayer.dto.PokemonDto;

/**
 * Created by sarsovsk on 16.01.2017.
 */
public class PokemonMapper {

  private static final String YELLOW_COLOR = "#FFFF00";

  public static PokemonDto mapPokemonDbModelToDto(Pokemon pokemon) {

    PokemonDto dto = new PokemonDto();

    dto.setId(pokemon.getId());
    dto.setName(pokemon.getName());
    dto.setColor(pokemon.getColor());
    dto.setType(pokemon.getType());

    boolean deletable =
        !StringUtils.equalsIgnoreCase(YELLOW_COLOR, pokemon.getColor());
    dto.setDeletable(deletable);

    return dto;
  }

  public static Pokemon mapPokemonDtoToDbModel(PokemonDto dto) {

    Pokemon pokemon = new Pokemon();

    pokemon.setId(dto.getId());
    pokemon.setName(dto.getName());
    pokemon.setColor(dto.getColor());
    pokemon.setType(dto.getType());

    return pokemon;
  }

}
