(function(angular) {
    var app = angular.module('indexApp', ['ngTouch', 'ngMessages','ui.router','oc.lazyLoad', /* 'ngImgCrop',*/'ui.bootstrap']);
    
    app.controller('indexCtrl', ['$scope', '$http', function($scope, $http) {
        //去掉底部多余空白
        setTimeout(function() {
            $('#trans-tooltip').parent().remove();
        }, 50);          
    }]);

    app.config(['$stateProvider', '$logProvider', '$urlRouterProvider', function($stateProvider, $logProvider, $urlRouterProvider) {
        $logProvider.debugEnabled(true);
        $urlRouterProvider.otherwise('/');
        
        $stateProvider
        .state("home", {
            url: '/',
            templateUrl: 'manager/test1.html',
            controller: 'Test1Contorller',
            controllerAs: 'Test1Ctrl',
            resolve: {
                deps:['$stateParams', '$ocLazyLoad',function($stateParams, $ocLazyLoad) {
                    return $ocLazyLoad.load('manager/js/test1/list.js');
                }]
            },
            sidebarMeta: {
                order: 800
            }
        }).state("test", {
            url: '/test',
            templateUrl: 'manager/test2.html',
            controller: 'Test2Contorller',
            controllerAs: 'Test2Ctrl',
            resolve: {
                deps:['$stateParams', '$ocLazyLoad',function($stateParams, $ocLazyLoad) {
                    return $ocLazyLoad.load('manager/js/test2/list.js');
                }]
            },
            sidebarMeta: {
                order: 800
            }
        });
    }]);
})(window.angular);