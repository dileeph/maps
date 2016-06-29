var pdfControllers = angular.module('pdfControllers', ['pdf']);

pdfControllers.controller('PdfController', function($scope, $http, $log){
	$scope.pdfName = 'Drones';
	$scope.pdfUrl = 'images/Drones.pdf';
	$scope.scroll = 0;
	$scope.loading = 'loading';
	$scope.pageNum=1;

	
	$scope.getNavStyle = function(scroll) {
	if(scroll > 100) return 'pdf-controls fixed';
		else return 'pdf-controls';
	}

	$scope.onError = function(error) {
		console.log(error);
	}

	$scope.onLoad = function() {
		$scope.loading = '';
		$scope.pageNum=1;
	}

	$scope.onProgress = function(progress) {
		console.log(progress);
	}
});