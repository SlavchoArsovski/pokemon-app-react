var homeModel = {

    init: function (afterInit) {

        var url = '/pokemon-app/pokemonList';
        var data = {};
        var me = this;

        $.ajax({
            url: url,
            data: data,
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function(responseData) {
                me.update(responseData, true);

                if (afterInit) {
                    afterInit();
                }
            },
            error: function(jqXHR) {
                console.log('error getting pokemon list');
                console.log(jqXHR);
            },
            complete: function() {

            },
            method: 'GET'
        });
    },

    _getState: function() {
        var url = '/pokemon-app/pokemonList';
        var data = {};
        var me = this;

        $.ajax({
            url: url,
            data: data,
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function(responseData) {
                me.update(responseData);
            },
            error: function(jqXHR) {
                console.log('error getting pokemon list');
                console.log(jqXHR);
            },
            complete: function() {

            },
            method: 'GET'
        });
    },

    update: function (newModel, noTrigger) {
        for (var key in newModel) {
            //copy all the fields
            this[key] = newModel[key];
        }

        if (noTrigger) {
            return;
        }
        $(this).trigger('modelchanged');
    },

    updatePokemon: function (pokemon) {

        var url = '/pokemon-app/updatePokemon';
        var data = {
            id: pokemon.id,
            name: pokemon.name,
            type: pokemon.type,
            color: pokemon.color
        };

        var self = this;

        $.ajax({
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function(responseData) {
                console.log(responseData);
                self._getState();
            },
            error: function(jqXHR) {
                console.log('error updating pokemon with status code ' +  jqXHR.status);
                console.log(jqXHR);
            },
            complete: function() {

            },
            method: 'PUT'
        });
    },

    deletePokemon: function (pokemonId) {

        var url = '/pokemon-app/deletePokemon/{pokemonId}';
        url = url.replace('{pokemonId}', pokemonId);

        var data = {};

        var self = this;

        $.ajax({
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function(responseData) {
                console.log(responseData);
                self._getState();
            },
            error: function(jqXHR) {
                console.log('error deleting pokemon with status code ' +  jqXHR.status);
                console.log(jqXHR);
            },
            complete: function() {

            },
            method: 'DELETE'
        });
    },

    addPokemon: function(pokemon) {

        var url = '/pokemon-app/addPokemon';
        var data = {
            name: pokemon.name,
            type: pokemon.type,
            color: pokemon.color
        };

        var self = this;

        $.ajax({
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function() {
                self._getState();
            },
            error: function(jqXHR) {
                console.log('error adding pokemon with status code ' +  jqXHR.status);
                console.log(jqXHR);
            },
            complete: function() {

            },
            method: 'POST'
        });
    },

    getModelForView: function () {
        return {
            pokemons: this.pokemons
        };
    }
};