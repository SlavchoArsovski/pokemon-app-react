package com.pokemonapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pokemonapp.servicelayer.dto.PokemonDto;
import com.pokemonapp.servicelayer.service.PokemonService;
import com.pokemonapp.web.viewmodel.PokemonViewModel;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * Controller for the home page.
 */
@Controller
public class HomeController {

  @Autowired
  private PokemonService pokemonServiceImpl;

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public ModelAndView home() {

    List<PokemonDto> pokemons = pokemonServiceImpl.getPokemonsForLoggedInUser();

    PokemonViewModel viewModel = new PokemonViewModel();
    viewModel.setPokemons(pokemons);

    ModelAndView modelAndView = new ModelAndView("home");
    modelAndView.addObject("viewBean", viewModel);

    return modelAndView;
  }
}
