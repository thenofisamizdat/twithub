(function() {
    'use strict';

    angular
        .module('twithubApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$rootScope','Auth','$scope', 'Principal', 'LoginService', '$state', 'Search'];

    function HomeController ($rootScope, Auth, $scope, Principal, LoginService, $state, Search) {
        var vm = this;

        vm.searchQuery = "";
        vm.search = search;
        vm.searches = [];

        vm.tweets = [];

        vm.selectProject = selectProject;

        vm.login = login;
        vm.viewAllGit = viewAllGit;

        vm.viewAllTweet = viewAllTweet;
        vm.allTweet = "";
        login();

        function login(){
            Auth.login({
                username: "user",
                password: "user",
                rememberMe: true
            }).then(function () {$rootScope.$broadcast('authenticationSuccess');})
        }

        function selectProject(id){
            vm.tweets = vm.searches[id].tweets;
            if (vm.tweets.length == 0){
                vm.tweets[0] = {};
                vm.tweets[0].text = "No Tweets found for " + vm.searches[id].project.name;
            }
        }

        function viewAllTweet(fullTweet){
            if (vm.allTweet) {
                vm.allTweet = "";
            }
            else {
                vm.allTweet = fullTweet;
            }
        }

        function viewAllGit(id){
            if (vm.searches[id].all) {
                vm.searches[id].all = "";
            }
            else {
                for (var i = 0; i < vm.searches.length; i++) vm.searches[i].all = "";
                vm.searches[id].all = vm.searches[id].project;
            }
        }

        function search(){
            Search.query({query: vm.searchQuery}, function(result) {
                vm.searches = angular.fromJson(result);
                //vm.searchQuery = null;
            });
        }

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }
    }
})();
