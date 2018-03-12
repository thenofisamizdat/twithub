(function() {
    'use strict';

    angular
        .module('twithubApp')
        .controller('SearchController', SearchController);

    SearchController.$inject = [ 'UserSearch'];

    function SearchController(UserSearch) {

        var vm = this;

        vm.searches = [];

        vm.searchQuery = "";
        vm.search = search;

        function search(){
            Search.query({query: vm.searchQuery}, function(result) {
                vm.searches = angular.fromJson(result);
                vm.searchQuery = null;
            });
        }

        loadAll();

        function loadAll() {
            UserSearch.query(function(result) {
                vm.searches = result;
                vm.searchQuery = null;
            });
        }
    }
})();
