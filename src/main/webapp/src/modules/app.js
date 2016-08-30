(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        "citAsModule",
        "pacienteModule",
        "ngMessages"
    ]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/citasList');
        }]);

  
})(window.angular);