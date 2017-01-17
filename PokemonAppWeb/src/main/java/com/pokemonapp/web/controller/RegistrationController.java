package com.pokemonapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pokemonapp.db.datamodel.User;
import com.pokemonapp.servicelayer.service.UserService;
import com.pokemonapp.web.validator.UserValidator;
import com.pokemonapp.web.viewmodel.UserViewModel;

/**
 * Registration controller.
 */
@Controller
public class RegistrationController {

    public static final String VIEW_BEAN = "userForm";
    public static final String REGISTRATION_VIEW_NAME = "registration";

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {

        model.addAttribute(VIEW_BEAN, new UserViewModel());

        return REGISTRATION_VIEW_NAME;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(
            @ModelAttribute("userForm") @Validated UserViewModel userViewModel,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(VIEW_BEAN, userViewModel);
            return REGISTRATION_VIEW_NAME;
        }

        User user = new User();
        user.setUserName(userViewModel.getUserName());
        user.setPassword(userViewModel.getPassword());
        user.setEnabled(true);

        userService.saveNewUser(user, "ROLE_USER");

        return "redirect:/login";
    }
}
