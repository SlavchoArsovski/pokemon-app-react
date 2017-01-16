$(document).ready(function() {

  $.ajaxSetup({ cache: false });

  homeModel.init(function() {
    homeView.initView(homeModel.getModelForView());
    homeView.updateView(homeModel.getModelForView());
  });


  $(homeModel).on('modelchanged', function() {
    homeView.updateView(homeModel.getModelForView());
  });

  $(homeView).on('addPokemon', function (event, pokemon) {
    homeModel.addPokemon(pokemon);
  });

  $(homeView).on('updatePokemon', function (event, pokemon) {
    homeModel.updatePokemon(pokemon);
  });

  $(homeView).on('deletePokemon', function (event, pokemonId) {
    homeModel.deletePokemon(pokemonId);
  });

});