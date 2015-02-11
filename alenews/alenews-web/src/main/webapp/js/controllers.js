'use strict';

var controllers = angular.module('controllers', []);

controllers.controller('contentListController', ['$scope', '$http', '$sce',
    function ($scope, $http, $sce) {
        $http.get('http://172.16.110.151:8080/alenews-web/content').success(function(data) {
            $scope.articles = data;
        });
    }]);

