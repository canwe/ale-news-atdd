'use strict';

var articleApp = angular.module('articleApp', ['ngRoute', 'articleControllers']);

articleApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/article/add', {
        templateUrl: 'partials/article/add.html'
      }).
      when('/article', {
        templateUrl: 'partials/article/list.html',
        controller: 'articleListController'
      }).
      otherwise({
        redirectTo: '/article'
      });

  }]);
