addEventListener('load', function() {
	console.log('LOADED!');
	var startButton = document.getElementById('startButton');
	startButton.addEventListener('click', getInitialCards);
});

var getInitialCards = function(e){
	e.preventDefault();
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'rest/deal');
	xhr.onreadystatechange = function() {
		if (xhr.status < 400 && xhr.readyState === 4) {
			
			// convert responseText to JSON
			var data = JSON.parse(xhr.responseText);
			console.log(data.toString());


		} else if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error('ERROR!!!!');
		}
	};
	xhr.send(null);
}// end of the initial dealing of cards
