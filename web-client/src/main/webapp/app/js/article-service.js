var articleApp = angular.module('articleApp', []);

articleApp.controller('ArticleListCtrl', ['$scope', '$http',
  function ($scope, $http) {
    $http.get('http://localhost:8080/article-service/article').success(function(data) {
      $scope.articles = data;
    });
  }]);
