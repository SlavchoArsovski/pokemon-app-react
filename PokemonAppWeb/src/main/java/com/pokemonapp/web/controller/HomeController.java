package com.pokemonapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pokemonapp.db.datamodel.Pokemon;
import com.pokemonapp.servicelayer.service.PokemonService;
import com.pokemonapp.web.viewmodel.HomeViewModel;

/**
 * Created by sarsovsk on 15.01.2017.
 */
@Controller
public class HomeController {

  @Autowired
  private PokemonService pokemonService;

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public ModelAndView home() {

    List<Pokemon> pokemons = pokemonService.getAllPokemons();

    HomeViewModel viewModel = new HomeViewModel();
    viewModel.setPokemons(pokemons);

    ModelAndView modelAndView = new ModelAndView("home");
    modelAndView.addObject("viewBean", viewModel);

    return modelAndView;
  }
}
