'use strict';

var controllers = angular.module('controllers', []);

controllers.controller('contentListController', ['$scope', '$http', '$sce',
    function ($scope, $http, $sce) {
        $http.get('/alenews-web/content').success(function(data) {
            $scope.articles = data;
        });
    }]);

