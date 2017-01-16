$(document).ready(function () {

    $.ajaxSetup({cache: false});

    homeModel.init(function () {
        homeView.initView(homeModel.getModelForView());
        homeView.updateView(homeModel.getModelForView());
    });


    $(homeModel).on('modelchanged', function () {
        homeView.updateView(homeModel.getModelForView());
    });

});