var homeView = {

  pageComponents: {
    pokemonList: '.pokemon-overview-table',
    pokemonInfoFirstRow: '.pokemon-first-row',
    pokemonInfoSecondRow: '.pokemon-second-row'
  },

  initView: function(model) {
    this._initViewChangingEvents();
    this._initModelChangingEvents();
  },

  _initViewChangingEvents: function() {

    var me = this;

    $(me.pageComponents.pokemonInfoFirstRow).click(function(element) {
      var pokemonId = $(this).data('pokemonid');
      $('#pokemon-second-row-' + pokemonId).toggle();
    });

  },

  _initModelChangingEvents: function() {

  },

  updateView: function(model) {

  },

};