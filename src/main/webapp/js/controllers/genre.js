(function() {
	var app = angular.module("genre", []);
	
	app.directive("genre", function(){
		return {
			restrict: 'E',
			templateUrl: "searchByGenre.html",
			controller: 
				function($http) {
				var self = this;
				self.title = "search Genre Ctrl";
				console.log(self.title);
				self.searchGenre;
				
				self.responses;
				self.genres = [];
				self.details;
				self.genrs; // pour rechercher les details
				self.actors;
				self.id;
				self.poster;
				self.overview;
				self.release_date;
				self.title;
				
				// https://api.themoviedb.org/3/search/person?api_key=08b5b405b64319954ebd67db9a6579cd&language=en-US&query=will&page=1&include_adult=false
				
				self.URL = "https://api.themoviedb.org/3/";
				self.SEARCH = "search/person";
				self.API_KEY = "?api_key=08b5b405b64319954ebd67db9a6579cd";
				self.LANGUAGE = "&language=fr-FR";
				//self.query = "&query="+self.searchGenre;
				self.query = self.searchGenre;
				self.PAGE = "&page=1";
				self.INCLUDE = "&include_adult=false";
				
				$http({
					method : 'GET',
					url : 'http://localhost:8080/theMovieDbApi/services/getAllGenre/'
				}).then(function successCallback(response) {
					self.genres = response.data;
					console.log(self.genres);
				}, function errorCallback(response) {

				});
				
				// recherche par genres
				self.getByGenre = function()
				{
					self.responses={};
					console.log("get film by genre ");
					
					console.log("J'ai cliqué sur rechercher par genre ");					
					console.log(self.searchGenre);
					console.log("Query = "+self.searchGenre);
					
					$http({
						method : 'GET',
						url : 'http://localhost:8080/theMovieDbApi/services/getFilmByGenre/'+self.searchGenre
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
					self.genrs = [];
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
								if(self.genrs.indexOf(detail.data[i][4]) == -1)
								{
									self.genrs.push(detail.data[i][4]);
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
						console.log(self.genrs);
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
			controllerAs: 'genreCtrl'
		};
	});
})(); 