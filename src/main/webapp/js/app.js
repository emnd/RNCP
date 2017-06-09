
(function() {
	var app = angular.module("movie", [ "pascalprecht.translate", "now","title","genre","actor","videotheque"]);

	app.config(function($translateProvider) {
		$translateProvider.useStaticFilesLoader({
		    prefix: 'i18n/messages-',
		    suffix: '.json'
		});
		$translateProvider.preferredLanguage('en');
		$translateProvider.useSanitizeValueStrategy('escape');
	});
	
	app.controller('TranslateController', function($translate) {
	  this.changeLanguage = function (langKey) {
	    $translate.use(langKey);
	  };
	});

	app.controller("PanelController", function() {
		var self = this;
		self.tab   = null;
		self.setTab = function(currentTab) {
			self.tab = currentTab;
		};

		self.isSet = function(currentTab) {
			return self.tab == currentTab;
		};
	});
	
})();