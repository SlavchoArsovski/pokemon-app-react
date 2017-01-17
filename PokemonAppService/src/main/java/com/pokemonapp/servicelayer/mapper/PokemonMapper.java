package com.pokemonapp.servicelayer.mapper;

import org.apache.commons.lang3.StringUtils;

import com.pokemonapp.db.datamodel.Pokemon;
import com.pokemonapp.servicelayer.dto.PokemonViewDto;

/**
 * Created by sarsovsk on 16.01.2017.
 *
 * Contains methods for mapping pokemon data model to view model and vice versa.
 */
public class PokemonMapper {

  private static final String YELLOW_COLOR = "#FFFF00";

  /**
   * Maps db model to view model.
   *
   * @param pokemon the pokemon db model to be mapped.
   * @return mapped view model.
   */
  public static PokemonViewDto mapPokemonDbModelToDto(Pokemon pokemon) {

    PokemonViewDto dto = new PokemonViewDto();

    dto.setId(pokemon.getId());
    dto.setName(pokemon.getName());
    dto.setColor(pokemon.getColor());
    dto.setType(pokemon.getType());

    boolean deletable =
        !StringUtils.equalsIgnoreCase(YELLOW_COLOR, pokemon.getColor());
    dto.setDeletable(deletable);

    return dto;
  }

  /**
   * Maps view model to db model.
   *
   * @param dto the the view model to be mapped.
   * @return mapped db model.
   */
  public static Pokemon mapPokemonDtoToDbModel(PokemonViewDto dto) {

    Pokemon pokemon = new Pokemon();

    pokemon.setId(dto.getId());
    pokemon.setName(dto.getName());
    pokemon.setColor(dto.getColor());
    pokemon.setType(dto.getType());

    return pokemon;
  }

}
