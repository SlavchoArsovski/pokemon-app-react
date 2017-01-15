package com.pokemonapp.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sarsovsk on 15.01.2017.
 */
@RestController
public class Hello {

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String sayHello() {
    return "Hello";
  }

}
