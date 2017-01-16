var homeView = {

    pageComponents: {
        pokemonList: '#realEstateTypeDropdown'
    },

    initView: function (model) {
        this._initViewChangingEvents();
        this._initModelChangingEvents();
    },

    _initViewChangingEvents: function () {

    },

    _initModelChangingEvents: function () {

    },

    updateView: function (model) {

        this._updateSearchProperties(model.validProperties);
        this._updateRealEstateType(model.selectedRealEstateType);
    },

};