(function() {
    'use strict';

    angular
        .module('twithubApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('search', {
            parent: 'entity',
            url: '/search',
            data: {

                pageTitle: 'Searches'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/search/searches.html',
                    controller: 'SearchController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('search-detail', {
            parent: 'search',
            url: '/search/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Search'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/search/search-detail.html',
                    controller: 'SearchDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Search', function($stateParams, Search) {
                    return Search.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'search',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('search-detail.edit', {
            parent: 'search-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/search/search-dialog.html',
                    controller: 'SearchDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Search', function(Search) {
                            return Search.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('search.new', {
            parent: 'search',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/search/search-dialog.html',
                    controller: 'SearchDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                query: null,
                                timestamp: null,
                                userId: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('search', null, { reload: 'search' });
                }, function() {
                    $state.go('search');
                });
            }]
        })
        .state('search.edit', {
            parent: 'search',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/search/search-dialog.html',
                    controller: 'SearchDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Search', function(Search) {
                            return Search.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('search', null, { reload: 'search' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('search.delete', {
            parent: 'search',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/search/search-delete-dialog.html',
                    controller: 'SearchDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Search', function(Search) {
                            return Search.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('search', null, { reload: 'search' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
