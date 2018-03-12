(function() {
    'use strict';
    angular
        .module('twithubApp')
        .factory('Search', Search);

    Search.$inject = ['$resource'];

    function Search ($resource) {
        var resourceUrl =  'api/searches/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true, ignore401: true},
            'get': {
                method: 'GET',
                ignore401: true,
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
