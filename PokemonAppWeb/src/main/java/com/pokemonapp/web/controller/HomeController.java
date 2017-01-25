package com.pokemonapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pokemonapp.servicelayer.dto.PokemonViewDto;
import com.pokemonapp.servicelayer.service.PokemonService;
import com.pokemonapp.web.viewmodel.PokemonViewModel;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * Controller for the home page.
 */
@Controller
public class HomeController {

  private static final String SERVLET_CONTEXT_PATH = "SERVLET_CONTEXT_PATH";

  @Value("#{servletContext.contextPath}")
  private String servletContextPath;

  private static final String HOME_VIEW_NAME = "home";
  private static final String VIEW_BEAN = "viewBean";

  @Autowired
  private PokemonService pokemonServiceImpl;

  /**
   * Handler for home page.
   *
   * @return {@link ModelAndView}.
   */
  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public ModelAndView home() {

    List<PokemonViewDto> pokemons = pokemonServiceImpl.getAllPokemons();

    PokemonViewModel viewModel = new PokemonViewModel();
    viewModel.setPokemons(pokemons);

    ModelAndView modelAndView = new ModelAndView(HOME_VIEW_NAME);
    modelAndView.addObject(VIEW_BEAN, viewModel);
    modelAndView.addObject(SERVLET_CONTEXT_PATH, servletContextPath);

    return modelAndView;
  }
}
