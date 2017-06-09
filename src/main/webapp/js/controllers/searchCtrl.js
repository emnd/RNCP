
(function() {
	var app = angular.module("app");
	app.controller("searchCtrl",searchCtrl);
	
	function searchCtrl($scope,$http)
	{
		$scope.title = "search Ctrl";
		console.log($scope.title);
		$scope.serachActors;
		
		$scope.responses;
		$scope.know_for;
		
		// https://api.themoviedb.org/3/search/person?api_key=08b5b405b64319954ebd67db9a6579cd&language=en-US&query=will&page=1&include_adult=false
		
		$scope.URL = "https://api.themoviedb.org/3/";
		$scope.SEARCH = "search/person";
		$scope.API_KEY = "?api_key=08b5b405b64319954ebd67db9a6579cd";
		$scope.LANGUAGE = "&language=fr-FR";
		$scope.query = "&query="+$scope.serachActors;
		$scope.PAGE = "&page=1";
		$scope.INCLUDE = "&include_adult=false";
		
		// recherche par acteurs
		$scope.getActors = function()
		{
			console.log($scope.serachActors);
			$scope.query = "&query="+$scope.serachActors;
			console.log("Query = "+$scope.query);
			//var promise = $http.get(URL+SEARCH+API_KEY+LANGUAGE+QUERY+$scope.serachActors+PAGE+INCLUDE);
			var promise = $http.get($scope.URL+$scope.SEARCH+$scope.API_KEY+$scope.LANGUAGE+$scope.query+$scope.PAGE+$scope.INCLUDE);
			
			promise.then(successCallback,faillureCallback);
			function successCallback (result)
			{
				console.log(" succes  :");
				console.log(result);
				$scope.responses = result.data.results;
				for(var i=0; i<$scope.responses.length;i++)
					{
						$scope.know_for.push($scope.responses[i]);
					}
				console.log("le know_for : ");
				console.log($scope.know_for);
				
			}
			 function faillureCallback (result)
			{
				 $scope.responses=null;
				 console.log(" faillure  :"+result);
			}
		};
		
		// liste des films en salle actuellement
		$scope.getNowPlaying = function()
		{
			var promise = $http.get("https://api.themoviedb.org/3/movie/now_playing?api_key=08b5b405b64319954ebd67db9a6579cd&language=fr-FR&page=1");
			
			promise.then(successCallback,faillureCallback);
			function successCallback (result)
			{
				console.log(" succes  :");
				console.log(result);
				$scope.responses = result.data.results;
				console.log("la reponse : "+$scope.responses);
				
			}
			 function faillureCallback (result)
			{
				 $scope.responses=null;
				 console.log(" faillure  :"+result);
			}
		};
		
		// recherche par genre
		$scope.getByGenre = function()
		{
			var promise = $http.get("https://api.themoviedb.org/3/genre/movie/list?api_key=08b5b405b64319954ebd67db9a6579cd&language=fr-FR");
			
			promise.then(successCallback,faillureCallback);
			function successCallback (result)
			{
				console.log(" succes  :");
				console.log(result);
				$scope.responses = result.data.results;
				console.log("la reponse : "+$scope.responses);
				
			}
			 function faillureCallback (result)
			{
				 $scope.responses=null;
				 console.log(" faillure  :"+result);
			}
		};
		
		// recherche par titre
		$scope.getByTitle = function()
		{
			var promise = $http.get("https://api.themoviedb.org/3/genre/movie/list?api_key=08b5b405b64319954ebd67db9a6579cd&language=fr-FR");
			
			promise.then(successCallback,faillureCallback);
			function successCallback (result)
			{
				console.log(" succes  :");
				console.log(result);
				$scope.responses = result.data.results;
				console.log("la reponse : "+$scope.responses);
				
			}
			 function faillureCallback (result)
			{
				 $scope.responses=null;
				 console.log(" faillure  :"+result);
			}
		};
	
	}
})();