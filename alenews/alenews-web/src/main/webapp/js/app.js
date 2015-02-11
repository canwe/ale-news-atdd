'use strict';

var alenewsApp = angular.module('alenewsApp', ['ngRoute', 'controllers']);

alenewsApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/timeline', {
                templateUrl: 'partials/timeline/list.html',
                controller: 'contentListController'
            }).
            otherwise({
                redirectTo: '/timeline'
            });

    }]);
