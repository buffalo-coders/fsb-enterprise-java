/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

const Controllers = angular.module('Controllers', []);
const Services = angular.module('Services', []);
const App = angular.module('App', [ 'ngMessages', 'ngRoute', 'Controllers', 'Services' ]);

// ---

App.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/greetings', {
		controller : 'GreetingsController',
		controllerAs : 'ctrl',
		templateUrl : 'greetings.html'
	});

	$routeProvider.when('/greetings/new', {
		controller : 'GreetingNewController',
		controllerAs : 'ctrl',
		templateUrl : 'greeting-new.html'
	});

	$routeProvider.when('/greetings/:id', {
		controller : 'GreetingController',
		controllerAs : 'ctrl',
		templateUrl : 'greeting.html'
	});

	$routeProvider.otherwise({
		redirectTo : '/greetings'
	});

} ]);

// ---

Controllers.controller('GreetingsController', [ '$location', 'GreetingsService',
		function($location, GreetingsService) {

			var ctrl = this;

			GreetingsService.getAll().then(function(response) {
				ctrl.greetings = response;
			});

			ctrl.doCreate = function() {
				$location.path('/greetings/new');
			}

} ]);

Controllers.controller('GreetingController', [
		'$location',
		'$routeParams',
		'GreetingsService',
		function($location, $routeParams, GreetingsService) {

			var ctrl = this;

			ctrl.id = $routeParams.id;

			GreetingsService.getById(ctrl.id).then(function(response) {
				ctrl.greeting = response;
			});

			ctrl.doCancel = function() {
				$location.path('/greetings');
			}

			ctrl.doDelete = function() {
				GreetingsService.deleteById(ctrl.id).then(function(response) {
					$location.path('/greetings');
				});
			}

			ctrl.doUpdate = function() {
				GreetingsService.updateById(ctrl.id, ctrl.greeting).then(
						function(response) {
							$location.path('/greetings');
						});
			}

		} ]);

Controllers.controller('GreetingNewController', [ '$location',
		'GreetingsService', function($location, GreetingsService) {

			var ctrl = this;

			ctrl.greeting = {};

			ctrl.doCancel = function() {
				$location.path('/greetings');
			}

			ctrl.doCreate = function() {
				GreetingsService.create(ctrl.greeting).then(function(response) {
					$location.path('/greetings');
				});
			}

		} ]);

// ---

Services.factory('GreetingsService', [ '$http', function($http) {

	return {
		'create' : function(greeting) {
			 return $http.post('/api/greetings', greeting).then(function (response) {
				 return response.data;
			 });
		},

		'deleteById' : function(id) {
			 return $http.delete('/api/greetings/' + id).then(function (response) {
				 return response.data;
			 });
		},

		'getAll' : function() {
			 return $http.get('/api/greetings').then(function (response) {
				 return response.data;
			 });
		},

		'getById' : function(id) {
			 return $http.get('/api/greetings/' + id).then(function (response) {
				 return response.data;
			 });
		},

		'updateById' : function(id, greeting) {
			 return $http.put('/api/greetings/' + id, greeting).then(function (response) {
				 return response.data;
			 });
		}
	};

} ]);

// ---
