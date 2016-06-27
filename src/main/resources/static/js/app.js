var myApp = angular.module('myApp', [
  'ngRoute',
  'searchControllers',
  'pdfControllers'
]);


myApp.config(['$routeProvider', 
	function($routeProvider){
		$routeProvider.
			when('/survey',{
				templateUrl: 'partials/search.html',
				controller : 'SurveySearchController'
			}).
			when('/pdf',{
				templateUrl: 'partials/pdf-loader.html',
				controller : 'PdfController'
			}).
			when('/media',{
				templateUrl: 'partials/image-view.html',
				controller : 'MediaController'
			}).
			otherwise({
				redirectTo: '/pdf'
			})
	}
]);