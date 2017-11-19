/*
 * http://stackoverflow.com/questions/18260815/use-gapi-client-javascript-to-execute-my-custom-google-api
 * https://developers.google.com/appengine/docs/java/endpoints/consume_js
 * https://developers.google.com/api-client-library/javascript/reference/referencedocs#gapiclientload
 *
 */

/**
 * After the client library has loaded, this init() function is called.
 * The init() function loads the helloworldendpoints API.
 */

function init() {
	
	// You need to pass the root path when you load your API
	// otherwise calls to execute the API run into a problem
	
	// rootpath will evaulate to either of these, depending on where the app is running:
	// //localhost:8080/_ah/api
	// //your-app-id/_ah/api

	var rootpath = "//" + window.location.host + "/_ah/api";
	
	// Load the helloworldendpoints API
	// If loading completes successfully, call loadCallback function
	gapi.client.load('helloworldendpoints', 'v1', loadCallback, rootpath);
}

/*
 * When helloworldendpoints API has loaded, this callback is called.
 * 
 * We need to wait until the helloworldendpoints API has loaded to
 * enable the actions for the buttons in index.html,
 * because the buttons call functions in the helloworldendpoints API
 */
function loadCallback () {	
	// Enable the button actions
	enableButtons ();
}

function enableButtons () {
	btn = document.getElementById("input_greet_by_name");
	btn.onclick=function(){greetByName()};
	
	// Update the button label now that the button is active
	btn.value="See Stats Now!";
}

/*
 * Execute a request to the sayHello() endpoints function
 */
function greetGenerically () {
	// Construct the request for the sayHello() function
	var request = gapi.client.helloworldendpoints.sayHello();
	
	// Execute the request.
	// On success, pass the response to sayHelloCallback()
	request.execute(sayHelloCallback);
}

/*
 * Execute a request to the sayHelloByName() endpoints function.
 * Illustrates calling an endpoints function that takes an argument.
 */
function greetByName () {
	// Get the name from the name_field element
	//var race = document.getElementById("race_id").value;
	var company = document.getElementById("company_id").value;
	
	// Call the sayHelloByName() function.
	// It takes one argument "name"
	// On success, pass the response to sayHelloCallback()
	//var request = gapi.client.helloworldendpoints.sayHelloByName({'race': race, 'company': company});
	var request = gapi.client.helloworldendpoints.sayHelloByName({'company': company});
	request.execute(sayHelloCallback);
}

// Process the JSON response
// In this case, just show an alert dialog box
// displaying the value of the message field in the response
function sayHelloCallback (response) {
	$( ".hide" ).hide();
	$('#results').html(response.message);
	$('#chartdiv').removeClass('hidden');
	displayChart(response);
	
	
}

function displayChart(response) {
	$(document).ready(function(){

	    var chart = AmCharts.makeChart( "chartdiv", {
	        "type": "pie",
	        "theme": "light",
	        "dataProvider": [ {
	            "job": "Professionals",
	            "count": 301.9
	        }, {
	            "job": "Administrative support",
	            "count": 301.9
	        }, {
	            "job": "Lead/Manager",
	            "count": 201.1
	        }, {
	            "job": "Operatives",
	            "count": 165.8
	        }, {
	            "job": "Technicians",
	            "count": 139.9
	        }, {
	            "job": "Craft workers",
	            "count": 128.3
	        }, {
	            "job": "Sales workers",
	            "count": 99
	        }, {
	            "job": "Service workers",
	            "count": 60
	        }, {
	            "job": "laborers and helpers",
	            "count": 50
	        } ],

	        "valueField": "count",
	        "titleField": "job",
	        "balloon":{
	            "fixedPosition":true
	        },
	        "export": {
	            "enabled": true
	        }

	    } );
	});
}




