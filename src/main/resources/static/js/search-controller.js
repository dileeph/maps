var searchControllers = angular.module('searchControllers', ['bootstrapLightbox','ngMap']);
	

searchControllers.controller('SurveySearchController', function($scope, $http, NgMap, $log){

	$scope.googleMapsUrl='https://maps.googleapis.com/maps/api/js?key=AIzaSyBSyA9nSTL6-bhKwD1SFsFU1Al4cPlGoWY';
	NgMap.getMap().then(function(map) {
		console.log(map.getCenter());
		console.log('markers', map.markers);
		console.log('shapes', map.shapes);
	
		$scope.results = [];
		$scope.markers = [];
		
		var marker;	
		var bounds;
		$scope.search = function(){	
			$http.post('./findsurveys', $scope.criteria)
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
				//bounds.extend($scope.markers[i].position);
				
			}
			//map.fitBounds(bounds);
			
		};
			
	});
	
});


searchControllers.controller('MediaController', function($scope, Lightbox,$http,  $log){
	$scope.images = [
		{
		  'url': 'images/DSC00084.JPG',
		  'caption': 'Image 1'
		 
		},
		{
		  'url': 'images/DSC00085.JPG',
		  'caption': 'Image 2'
		},
		{
			'type':'image',
		  'url': 'images/DSC00086.JPG',
		  'caption': 'Image 3'
		 },
		 
		{
		'type':'video',
		  'url': 'images/ezenergy.mp4',
		  'caption': 'Sample Video'
		 }
		 
		 
	  ];

	  $scope.openLightboxModal = function (index) {
		Lightbox.openModal($scope.images, index);
	  };
	
});