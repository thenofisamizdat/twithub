(function() {
    'use strict';
    angular
        .module('twithubApp')
        .factory('UserSearch', UserSearch);

    UserSearch.$inject = ['$resource'];

    function UserSearch ($resource) {
        var resourceUrl =  'api/userSearches/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
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
