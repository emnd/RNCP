(function() {
	var app = angular.module("title", []);
	
	app.directive("titleMovie", function(){
		return {
			restrict: 'E',
			templateUrl: "searchByTitle.html",
			controller: 
				function($http) {
				var self = this;
				self.title = "search Title Ctrl";
				console.log(self.title);
				self.searchTitle;
				
				self.responses;
				self.details;
				self.genres;
				self.actors;
				self.id;
				self.poster;
				self.overview;
				self.release_date;
				self.title;
				
				console.log("Mon titre");
				// https://api.themoviedb.org/3/search/movie?api_key=08b5b405b64319954ebd67db9a6579cd&language=fr-FR&query=titanic&page=1&include_adult=false
				
				self.URL = "https://api.themoviedb.org/3/";
				self.SEARCH = "search/person";
				self.API_KEY = "?api_key=08b5b405b64319954ebd67db9a6579cd";
				self.LANGUAGE = "&language=fr-FR";
				self.query = self.searchTitle;
				self.PAGE = "&page=1";
				self.INCLUDE = "&include_adult=false";
				
				// recherche par acteurs
				self.getByTitle = function()
				{
					console.log(self.serachTitle);
					console.log("Query = "+self.searchTitle);
					//var promise = $http.get(URL+SEARCH+API_KEY+LANGUAGE+QUERY+self.serachActors+PAGE+INCLUDE);
					self.responses={};
					console.log("get film by title ");
					
					console.log("J'ai cliqué sur rechercher par titre ");					
					console.log(self.searchTitle);
					console.log("Query = "+self.searchTitle);
					
					$http({
						method : 'GET',
						url : 'http://localhost:8080/theMovieDbApi/services/getFilmByTitle/'+self.searchTitle
					}).then(function successCallback(response) {
						self.responses = response.data;
						console.log(self.responses);
					}, function errorCallback(response) {

					});
				};
				
				// renvoyer les détails du film
				self.getDetail = function(id)
				{
					self.id = id;
					self.genres = [];
					self.actors = [];
					self.poster = [];
					self.overview = [];
					self.release_date = [];
					self.title = [];
					console.log("ID selectionné : "+self.id);
					self.details = {};
					$http({
						method : 'GET',
						url : 'http://localhost:8080/theMovieDbApi/services/getFilmDetail/'+self.id
					}).then(function successCallback(detail) {
						self.details = detail.data;
						console.log(self.details);
						// eviter les doublons dans les listes
						for(var i=0; i<self.details.length;i++)
						{
							//acteurs
								if( self.actors.indexOf(detail.data[i][5]) == -1 ) 
								{
									self.actors.push(detail.data[i][5]);
								}
								
								//genres
								if(self.genres.indexOf(detail.data[i][4]) == -1)
								{
									self.genres.push(detail.data[i][4]);
								}
								
								// poster
								if(self.poster.indexOf(detail.data[i][1]) == -1)
								{
									self.poster.push(detail.data[i][1]);
								}
								
								// résumé
								if(self.overview.indexOf(detail.data[i][3]) == -1)
								{
									self.overview.push(detail.data[i][3]);
								}
								
								// date de sortie en salle
								if(self.release_date.indexOf(detail.data[i][2]) == -1)
								{
									self.release_date.push(detail.data[i][2]);
								}
								
								// titre
								if(self.title.indexOf(detail.data[i][0]) == -1)
								{
									self.title.push(detail.data[i][0]);
								}
									
						}
						
						console.log(self.title);
						console.log(self.poster);
						console.log(self.genres);
						console.log(self.actors);
						console.log(self.release_date);
						console.log(self.overview);
					}, function errorCallback(detail) {

					});
					
				};
				
				self.getRetour = function()
				{
					self.details=null;
				};
				// fin detail
			}
				,
			controllerAs: 'titleCtrl'
		};
	});
})(); 