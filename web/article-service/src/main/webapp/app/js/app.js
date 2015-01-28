'use strict';

var articleApp = angular.module('articleApp', ['mgcrea.ngStrap', 'ngRoute', 'articleControllers']);

articleApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/article/add', {
        templateUrl: 'partials/article/add.html'
      }).
      when('/list', {
        templateUrl: 'partials/article/list.html',
        controller: 'articleListController'
      }).
      otherwise({
        redirectTo: '/list'
      });

  }]);
