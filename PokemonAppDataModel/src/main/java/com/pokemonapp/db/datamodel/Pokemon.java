package com.pokemonapp.db.datamodel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * Pokemon entity.
 */
@Entity
@Table(name = "POKEMON")
@Access(AccessType.FIELD)
public class Pokemon {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "POKEMON_ID_SEQUENCE_GENERATOR")
  @SequenceGenerator(
      name = "POKEMON_ID_SEQUENCE_GENERATOR",
      sequenceName = "POKEMON_ID_SEQUENCE",
      allocationSize = 1,
      initialValue = 10000)
  @Column(name = "ID", nullable = false, unique = true)
  private Long id;

  @Column(name = "NAME", length = 100, nullable = false)
  private String name;

  @Column(name = "TYPE", length = 50, nullable = false)
  private String type;

  @Column(name = "COLOR", length = 50, nullable = false)
  private String color;

  // no need for many - to - one for this application purpose
  @Column(name = "USER_NAME", nullable = false)
  private String userName;

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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
