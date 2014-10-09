'use strict';

var articleControllers = angular.module('articleControllers', []);

articleControllers.controller('articleListController', ['$scope', '$http',
    function ($scope, $http) {
        $http.get('http://localhost:8080/article-service/article').success(function(data) {
            $scope.articles = data;
        });
    }]);

articleControllers.controller('articleAddController', ['$scope', '$http',
    function ($scope, $http) {
        $scope.add = function(article) {
            $http.post('http://localhost:8080/article-service/article', $scope.article).success(function(data) {
                $scope.articleId = data;
            });
        } ;
    }]);
