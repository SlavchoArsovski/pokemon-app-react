var homeView = {

  pageComponents: {

    pokemonList: '.pokemon-overview-table',
    pokemonInfoRow: '.pokemon-row',
    pokemonRowTemplate: '.pokemon-row-template',

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
      me._pokemonRowClickHandler(this);
    });
  },

  _pokemonRowClickHandler: function(element) {

    var me = this;

    $(me.pageComponents.pokemonInfoRow).removeClass('pokemon-row-selected');

    me.selectedPokemon.id = $(element).find(me.pageComponents.pokemonId).text();
    me.selectedPokemon.name = $(element).find(me.pageComponents.pokemonName).text();
    me.selectedPokemon.type = $(element).find(me.pageComponents.pokemonType).text();
    me.selectedPokemon.color = $(element).find(me.pageComponents.pokemonColor).text();

    $(me.pageComponents.pokemonDetailId).text(me.selectedPokemon.id);
    $(me.pageComponents.pokemonDetailName).val(me.selectedPokemon.name);
    $(me.pageComponents.pokemonDetailType).val(me.selectedPokemon.type);
    $(me.pageComponents.pokemonDetailColor).val(me.selectedPokemon.color);

    $(element).addClass('pokemon-row-selected');

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

    var me = this;

    $(me.pageComponents.pokemonInfoRow).remove();

    $.each(model.pokemons, function (index, pokemon) {

      var templateHtml =
        $('<div>').append($(me.pageComponents.pokemonRowTemplate).clone()).html();

      templateHtml = templateHtml.replace('{pokemon.id}', pokemon.id);
      templateHtml = templateHtml.replace('{pokemon.name}', pokemon.name);
      templateHtml = templateHtml.replace('{pokemon.type}', pokemon.type);
      templateHtml = templateHtml.replace('{pokemon.color}', pokemon.color);
      templateHtml = templateHtml.replace('{pokemon.color}', pokemon.color);

      var row = $(templateHtml);
      row.removeClass('pokemon-row-template');
      row.addClass('pokemon-row');
      row.toggle();

      row.click(function() {
        me._pokemonRowClickHandler(this);
      });

      if (me.selectedPokemon.id && me.selectedPokemon.id == pokemon.id) {
        row.addClass('pokemon-row-selected');
      }

      $(me.pageComponents.pokemonList).append(row);

    });

  },

};