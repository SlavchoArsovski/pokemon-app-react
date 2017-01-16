var homeView = {

  pageComponents: {

    pokemonList: '.pokemon-overview-table',
    pokemonInfoRow: '.pokemon-row',

    pokemonId: '.pokemonId',
    pokemonName: '.pokemonName',
    pokemonType: '.pokemonType',
    pokemonColor: '.pokemonColorHolder',

    pokemonDetailId: '#pokemonDetailId',
    pokemonDetailName: '#pokemonDetailName',
    pokemonDetailType: '#pokemonDetailType',
    pokemonDetailColor: '#pokemonDetailColor',

    addPokemonBtn: '#addPokemonBtn',
    updatePokemonBtn: '#updatePokemonBtn',
    deletePokemonBtn: '#deletePokemonBtn'
  },

  selectedPokemon: {
    id: undefined,
    name: undefined,
    type: undefined,
    color: undefined
  },

  initView: function(model) {
    this._initViewChangingEvents();
    this._initModelChangingEvents();
  },

  _initViewChangingEvents: function() {

    var me = this;

    $(me.pageComponents.pokemonInfoRow).click(function() {


      $(me.pageComponents.pokemonInfoRow).removeClass('pokemon-row-selected');

      me.selectedPokemon.id = $(this).find(me.pageComponents.pokemonId).text();
      me.selectedPokemon.name = $(this).find(me.pageComponents.pokemonName).text();
      me.selectedPokemon.type = $(this).find(me.pageComponents.pokemonType).text();
      me.selectedPokemon.color = $(this).find(me.pageComponents.pokemonColor).text();

      $(me.pageComponents.pokemonDetailId).text(me.selectedPokemon.id);
      $(me.pageComponents.pokemonDetailName).val(me.selectedPokemon.name);
      $(me.pageComponents.pokemonDetailType).val(me.selectedPokemon.type);
      $(me.pageComponents.pokemonDetailColor).val(me.selectedPokemon.color);

      $(this).addClass('pokemon-row-selected');

    });

  },

  _initModelChangingEvents: function() {

    var me = this;

    $(me.pageComponents.deletePokemonBtn).on('click', function(event) {

      var selectedPokemonId = $(me.pageComponents.pokemonDetailId).text();

      if (selectedPokemonId) {
        $(me).trigger('deletePokemon', selectedPokemonId);
      }
    });

    $(me.pageComponents.updatePokemonBtn).on('click', function(event) {

      var selectedPokemonId = $(me.pageComponents.pokemonDetailId).text();

      if (selectedPokemonId) {

        var pokemon = {
          id: selectedPokemonId,
          name: $(me.pageComponents.pokemonDetailName).val(),
          type: $(me.pageComponents.pokemonDetailType).val(),
          color: $(me.pageComponents.pokemonDetailColor).val()
        };

        $(me).trigger('updatePokemon', pokemon);
      }

    });

    $(me.pageComponents.addPokemonBtn).on('click', function(event) {
      var pokemon = {
        name: $(me.pageComponents.pokemonDetailName).val(),
        type: $(me.pageComponents.pokemonDetailType).val(),
        color: $(me.pageComponents.pokemonDetailColor).val()
      };

      $(me).trigger('addPokemon', pokemon);

    });

  },

  updateView: function(model) {

    console.log('updateView');
    console.log(model);

  },

};