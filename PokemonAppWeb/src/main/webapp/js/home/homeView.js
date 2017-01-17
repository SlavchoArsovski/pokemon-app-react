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

    me.pokemonColorSelectMenu = $('#pokemon-color-selection').iconselectmenu({
      change: function(event, ui) {

        var selected = ui.item.value;
        me.selectedColor = selected;

        // update selected color
        $('.ui-selectmenu-icon').css('background-color', '');

        if (selected !== 'NO_COLOR') {
          $('.ui-selectmenu-icon').css('background-color', selected);
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
        $('.ui-selectmenu-icon').css('background-color', '');
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

    var allColors = [];

    $.each(model.pokemons, function(index, pokemon) {
      allColors.push(pokemon.color);
    });

    var uniqueColors = allColors.filter(function(elem, index, self) {
      return index == self.indexOf(elem);
    });

    if ($.inArray(me.selectedColor, uniqueColors) === -1) {
      me.selectedColor = 'NO_COLOR';
    }


    // remove old pokemon table raws
    $(me.pageComponents.pokemonInfoRow).remove();

    // render pokemon table raws
    $.each(model.pokemons, function(index, pokemon) {

      var templateHtml =
        $('<div>').append($(me.pageComponents.pokemonRowTemplate).clone()).html();

      templateHtml = templateHtml.replace('{pokemon.id}', pokemon.id);
      templateHtml = templateHtml.replace('{pokemon.name}', pokemon.name);
      templateHtml = templateHtml.replace('{pokemon.type}', pokemon.type);
      templateHtml = templateHtml.replace('<!--', '');
      templateHtml = templateHtml.replace('-->', '');
      templateHtml = templateHtml.replace('{pokemon.color}', pokemon.color);
      templateHtml = templateHtml.replace('{pokemon.color}', pokemon.color);

      var row = $(templateHtml);

      row.removeClass('pokemon-row-template');
      row.addClass('pokemon-row');

      if (me.selectedPokemon.id && me.selectedPokemon.id == pokemon.id) {
        row.addClass('pokemon-row-selected');
      }

      var showRow =
        me.selectedColor === 'NO_COLOR'
        || (me.selectedColor !== 'NO_COLOR' && me.selectedColor === pokemon.color);

      if (showRow) {
        row.show();
      }

      row.data('deletable', pokemon.deletable);

      row.click(function() {
        me._pokemonRowClickHandler(this);
      });

      $(me.pageComponents.pokemonList).append(row);

    });

    // update available colors
    $('.ui-selectmenu-icon').css('background-color', '');
    $('#pokemon-color-selection option').remove();


    $('#pokemon-color-selection')
      .append('<option value="NO_COLOR" data-class="pokemon-select-color-icon">Select Color</option>');

    $.each(uniqueColors, function(index, color) {

      var optionTemplate =
        '<option value="{pokemon.color}" data-class="pokemon-select-color-icon">{pokemon.color}</option>';

      optionTemplate = optionTemplate.replace('{pokemon.color}', color);
      optionTemplate = optionTemplate.replace('{pokemon.color}', color);

      $('#pokemon-color-selection').append(optionTemplate);
    });


    $('#pokemon-color-selection option[value="' + me.selectedColor + '"]').attr('selected', true);

    if (me.selectedColor !== 'NO_COLOR') {
      $('.ui-selectmenu-icon').css('background-color', me.selectedColor);
    }

    me.pokemonColorSelectMenu.iconselectmenu('refresh');
  }

};