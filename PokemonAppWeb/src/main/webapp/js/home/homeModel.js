var homeModel = {

    init: function (afterInit) {
        var url = '/pokemon-app/home/propertyChanged';
        var self = this;

        var data = {};

        $.ajax({
            dataType: "json",
            type: 'GET',
            url: url,
            cache: false,
            data: data,
            success: function (response) {
                self.update(response, true);
                if (afterInit) {
                    afterInit();
                }
            }
        });
    },

    _propertyChange: function (key, value) {
        var url = '/pokemon-app/home/propertyChanged';
        var data = {};
        var self = this;

        data[key] = value;
        data['changedProperty'] = key;

        $.ajax({
            url: url,
            type: "POST",
            data: data,
            success: function (response) {
                var newModel = response;
                self.update(newModel);
            }
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

    getModelForView: function () {
        return {
            pokemons: this.pokemons
        };
    }
};