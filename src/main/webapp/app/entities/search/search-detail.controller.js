(function() {
    'use strict';

    angular
        .module('twithubApp')
        .controller('SearchDetailController', SearchDetailController);

    SearchDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Search'];

    function SearchDetailController($scope, $rootScope, $stateParams, previousState, entity, Search) {
        var vm = this;

        vm.search = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('twithubApp:searchUpdate', function(event, result) {
            vm.search = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
