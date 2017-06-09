(function() {
	var app = angular.module("videotheque", []);
	
	app.directive("videotheque", function(){
		return {
			restrict: 'E',
			templateUrl: "videotheque.html",
			controller: 
				
				function($http) {
				var self = this;
				self.title = "search Now Ctrl";
				console.log(self.title);
				self.searchNow="Film en salle actuellement !";
				
				self.responses;
				self.details;
				self.genres;
				self.actors;
				self.id;
				self.poster;
				self.overview;
				self.release_date;
				self.title;
				
				// https://api.themoviedb.org/3/movie/now_playing?api_key=08b5b405b64319954ebd67db9a6579cd&language=fr-FR&page=1
				
				self.URL = "https://api.themoviedb.org/3/";
				self.SEARCH = "search/person";
				self.API_KEY = "?api_key=08b5b405b64319954ebd67db9a6579cd";
				self.LANGUAGE = "&language=fr-FR";
				self.query = "&query="+self.serachActors;
				self.PAGE = "&page=1";
				self.INCLUDE = "&include_adult=false";
				
				// recherche actuellemnet en salle
				 
				 self.list = function() {
					 self.responses={};
					 self.username = "ajcopen";
						$http({
							method : 'GET',
							url : 'http://localhost:8080/theMovieDbApi/services/getVideoTheque/'+self.username
						}).then(function successCallback(response) {
							self.responses = response.data;
							console.log(self.responses);
						}, function errorCallback(response) {

						});
					};
					
					self.list();
					
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
							
							console.log(self.id);
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
					
					// debut videotheque ajout
					self.addVideotheque = function(id)
					{
						self.id = id;
						self.username = "ajcopen";
						console.log("Film à ajouter à ma videothèque : "+self.id);
						$http({
							method : 'GET',
							url : 'http://localhost:8080/theMovieDbApi/services/addfilmToVideoTheque/'+self.id+'/'+self.username
						}).then(function successCallback(response) {
							console.log("Succès la videothèque");
							console.log(response);
						}, function errorCallback(response) {
							console.log("erreur lors de l'ajout du film à la videothèque");
						});
						
					};
					//fin videotheque ajout
					
					//debut enlever un film dans la videothèque
					self.deleteVideotheque = function(id)
					{
						self.id = id;
						self.username = "ajcopen";
						console.log("Film à enlever à ma videothèque : "+self.id);
						$http({
							method : 'PUT',
							url : 'http://localhost:8080/theMovieDbApi/services/deletefilmToVideoTheque/'+self.id+'/'+self.username
						}).then(function successCallback(response) {
							console.log("Succès la videothèque");
							console.log(response);
							self.list();
						}, function errorCallback(response) {
							console.log("erreur lors de la suppression du film à la videothèque");
						});
						
					};
					// fin enlever un film dans la videotheque
			}
				,
			controllerAs: 'videothequeCtrl'
		};
	});
})(); 