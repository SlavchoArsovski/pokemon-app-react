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
 * User Role Entity.
 */
@Entity
@Table(name = "PORTAL_USER_ROLES")
@Access(AccessType.FIELD)
public class UserRole {

  @Id
  @Column(name = "USER_ROLE_ID")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_ROLE_ID_SEQUENCE_GENERATOR")
  @SequenceGenerator(
      name = "USER_ROLE_ID_SEQUENCE_GENERATOR",
      sequenceName = "USER_ROLE_ID_SEQUENCE",
      allocationSize = 1,
      initialValue = 1)
  private Long id;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "ROLE")
  private String role;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
