package com.pokemonapp.servicelayer.dto;

/**
 * Created by sarsovsk on 16.01.2017.
 *
 * Pokemon Dto.
 */
public class PokemonViewDto {

  private Long id;
  private String name;
  private String type;
  private String color;
  private boolean deletable;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public boolean isDeletable() {
    return deletable;
  }

  public void setDeletable(boolean deletable) {
    this.deletable = deletable;
  }
}
