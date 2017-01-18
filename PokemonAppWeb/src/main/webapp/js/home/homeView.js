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
    deletePokemonBtn: '#deletePokemonBtn',

    pokemonSelectionColorList: '#pokemon-color-selection',
    pokemonSelectionColorListItems: '#pokemon-color-selection option',
    pokemonSelectionColorListIcon: '.ui-selectmenu-icon'
  },

  pokemonColorSelectMenu: undefined,
  selectedColor: 'NO_COLOR',

  selectedPokemon: {
    id: undefined,
    name: undefined,
    type: undefined,
    color: undefined,
    deletable: undefined
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

    $.widget('custom.iconselectmenu', $.ui.selectmenu, {
      _renderItem: function(ul, item) {
        var li = $('<li>'),
          wrapper = $('<div>', { text: item.label });

        if (item.disabled) {
          li.addClass("ui-state-disabled");
        }

        if (item.value !== 'NO_COLOR') {
          $('<span>', {
            style: 'background-color: ' + item.value,
            'class': 'ui-icon ' + item.element.attr('data-class')
          }).appendTo(wrapper);
        }

        return li.append(wrapper).appendTo(ul);
      }
    });

    me.pokemonColorSelectMenu = $(me.pageComponents.pokemonSelectionColorList).iconselectmenu({
      change: function(event, ui) {

        var selected = ui.item.value;
        me.selectedColor = selected;

        // update selected color
        $(me.pageComponents.pokemonSelectionColorListIcon).css('background-color', '');

        if (selected !== 'NO_COLOR') {
          $(me.pageComponents.pokemonSelectionColorListIcon).css('background-color', selected);
        }

        // filter pokemons by selected color
        if (selected !== 'NO_COLOR') {
          $(me.pageComponents.pokemonInfoRow).each(function(index, element) {
            if ($(element).find('.pokemonColorHolder').text() !== ui.item.value) {
              $(element).hide();
            } else {
              $(element).show();
            }
          });
        } else {
          $(me.pageComponents.pokemonInfoRow).show();
        }

      },
      create: function(event, ui) {
        $(me.pageComponents.pokemonSelectionColorListIcon).css('background-color', '');
      }
    });
  },

  _pokemonRowClickHandler: function(element) {

    var me = this;

    $(me.pageComponents.pokemonInfoRow).removeClass('pokemon-row-selected');
    $(me.pageComponents.deletePokemonBtn).prop("disabled", false);

    me.selectedPokemon.id = $(element).find(me.pageComponents.pokemonId).text();
    me.selectedPokemon.name = $(element).find(me.pageComponents.pokemonName).text();
    me.selectedPokemon.type = $(element).find(me.pageComponents.pokemonType).text();
    me.selectedPokemon.color = $(element).find(me.pageComponents.pokemonColor).text();
    me.selectedPokemon.deletable = $(element).data('deletable');

    $(me.pageComponents.pokemonDetailId).text(me.selectedPokemon.id);
    $(me.pageComponents.pokemonDetailName).val(me.selectedPokemon.name);
    $(me.pageComponents.pokemonDetailType).val(me.selectedPokemon.type);
    $(me.pageComponents.pokemonDetailColor).val(me.selectedPokemon.color);

    if (!me.selectedPokemon.deletable) {
      $(me.pageComponents.deletePokemonBtn).prop("disabled", true)
    }

    $(element).addClass('pokemon-row-selected');

  },

  _initModelChangingEvents: function() {

    var me = this;

    $(me.pageComponents.deletePokemonBtn).on('click', function(event) {

      var selectedPokemonId = $(me.pageComponents.pokemonDetailId).text();

      if (selectedPokemonId) {
        $(me).trigger('deletePokemon', selectedPokemonId);
      }

      $(me.pageComponents.pokemonDetailId).text('');
      $(me.pageComponents.pokemonDetailName).val('');
      $(me.pageComponents.pokemonDetailType).val('');
      $(me.pageComponents.pokemonDetailColor).val('');
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

    var pokemonColors = me._getAllPokemonColors(model.pokemons);

    if ($.inArray(me.selectedColor, pokemonColors) === -1) {
      me.selectedColor = 'NO_COLOR';
    }

    me._updatePokemonRows(model.pokemons);
    me._updatePokemonSelectionColorList(pokemonColors);
  },

  _getAllPokemonColors: function(pokemons) {

    var allColors = [];

    $.each(pokemons, function(index, pokemon) {
      allColors.push(pokemon.color);
    });

    return allColors.filter(function(elem, index, self) {
      return index == self.indexOf(elem);
    });
  },

  _updatePokemonRows: function(pokemons) {

    var me = this;

    // remove old pokemon table raws
    $(me.pageComponents.pokemonInfoRow).remove();

    // render pokemon table raws
    $.each(pokemons, function(index, pokemon) {

      var templateHtml = me._createPokemonRowHtml(pokemon);
      var row = $(templateHtml);

      row.removeClass('pokemon-row-template');
      row.addClass('pokemon-row');

      if (me.selectedPokemon.id && me.selectedPokemon.id == pokemon.id) {
        row.addClass('pokemon-row-selected');
      }

      var showRow = me.selectedColor === 'NO_COLOR'
        || (me.selectedColor !== 'NO_COLOR' && me.selectedColor === pokemon.color);

      if (showRow) {
        row.show();
      }

      row.data('deletable', pokemon.deletable);

      // add click handler.
      row.click(function() {
        me._pokemonRowClickHandler(this);
      });

      $(me.pageComponents.pokemonList).append(row);
    });
  },

  _createPokemonRowHtml: function(pokemon) {

    var me = this;
    var templateHtml =
      $('<div>').append($(me.pageComponents.pokemonRowTemplate).clone()).html();

    templateHtml = templateHtml.replace('{pokemon.id}', pokemon.id);
    templateHtml = templateHtml.replace('{pokemon.name}', pokemon.name);
    templateHtml = templateHtml.replace('{pokemon.type}', pokemon.type);
    templateHtml = templateHtml.replace('<!--', '');
    templateHtml = templateHtml.replace('-->', '');
    templateHtml = templateHtml.replace('{pokemon.color}', pokemon.color);
    templateHtml = templateHtml.replace('{pokemon.color}', pokemon.color);

    return templateHtml;
  },

  _updatePokemonSelectionColorList: function(colors) {

    var me = this;

    $(me.pageComponents.pokemonSelectionColorListIcon).css('background-color', '');
    $(me.pageComponents.pokemonSelectionColorListItems).remove();


    $(me.pageComponents.pokemonSelectionColorList)
      .append('<option value="NO_COLOR" data-class="pokemon-select-color-icon">Select Color</option>');

    $.each(colors, function(index, color) {

      var optionTemplate =
        '<option value="{pokemon.color}" data-class="pokemon-select-color-icon">{pokemon.color}</option>';

      optionTemplate = optionTemplate.replace('{pokemon.color}', color);
      optionTemplate = optionTemplate.replace('{pokemon.color}', color);

      $(me.pageComponents.pokemonSelectionColorList).append(optionTemplate);
    });


    $(me.pageComponents.pokemonSelectionColorListItems + '[value="' + me.selectedColor + '"]').attr('selected', true);

    if (me.selectedColor !== 'NO_COLOR') {
      $(me.pageComponents.pokemonSelectionColorListIcon).css('background-color', me.selectedColor);
    }

    me.pokemonColorSelectMenu.iconselectmenu('refresh');
  }

};