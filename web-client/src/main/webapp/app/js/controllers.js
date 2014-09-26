'use strict';

var articleControllers = angular.module('articleControllers', []);

articleControllers.controller('articleListController', ['$scope', '$http',
    function ($scope, $http) {
        $http.get('http://localhost:8080/article-service/article').success(function(data) {
            $scope.articles = data;
        });
    }]);
