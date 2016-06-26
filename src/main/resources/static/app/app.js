var app = angular.module('myApp', []);

app.controller('searchController', function($scope, $http, $log){
	
	$scope.results = [];
	$scope.markers = [];

	
	$scope.search = function(){
		
		
		
		$http.post('/findsurveys', $scope.criteria)
			.then(function successCallback(response) {
				
				$log.debug(response.data);
				$scope.results = response.data;
				for(i=0; i < $scope.results.length; i++){
					marker = new google.maps.Marker({
						position: {lat: $scope.results[i].latitude, lng:  $scope.results[i].longitude},
						map: map,
						zoom:6
					});
					$scope.markers.push(marker);
					
				}
				//map.setZoom(4);
				$scope.setMarkers();
			}, function errorCallback(response) {
				$log.debug(response.status);
				$log.debug(response.data);
			});
	

		
	};
	
	
	$scope.selectResult = function(lat, lon){
		$log.debug(" lat " + lat);
		
		marker = new google.maps.Marker({
			position: {lat: lat, lng: lon},
			map: map
		});
		$scope.clearMarkers();
		//map.setZoom(6);
		$scope.markers.push(marker);
		$scope.setMarkers();
		
	};
	
	$scope.clearMarkers = function(){
		for(i=0; i < $scope.markers.length; i++){
			$scope.markers[i].setMap(null);	
		
		}
		$scope.markers = [];
	};
	
	$scope.setMarkers = function(){
		for(i=0; i < $scope.markers.length; i++){
			$scope.markers[i].setMap(map);
			bounds.extend($scope.markers[i].position);
			
		}
		map.fitBounds(bounds);
		
	};
});