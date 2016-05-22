addEventListener('load', function() {
	console.log('LOADED!');
	var startButton = document.getElementById('startButton');
	startButton.addEventListener('click', getInitialCards);
});

var getInitialCards = function(e){
	e.preventDefault();
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'rest/dealPlayer');
	xhr.onreadystatechange = function() {
		if (xhr.status < 400 && xhr.readyState === 4) {
			// convert responseText to JSON
			var data = JSON.parse(xhr.responseText);
			console.log(data.toString());	
			var h3 = document.createElement('h3');
			h3.textContent = 'Players hand' ; 
			document.body.appendChild(h3);
			data.forEach(function(val,ind,arr){
				var cardSuit = document.createElement('h3');
				cardSuit.textContent = (val.rank + ' of ' + val.suit) ;
				document.body.appendChild(cardSuit);
			})
		} else if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error('ERROR!!!!');
		}
	};
	xhr.send(null);

	var xhr1 = new XMLHttpRequest();
	xhr1.open('GET', 'rest/dealDealer');
	xhr1.onreadystatechange = function() {
		if (xhr1.status < 400 && xhr1.readyState === 4) {
			// convert responseText to JSON
			var data = JSON.parse(xhr1.responseText);
			console.log(data.toString());	
			var h3 = document.createElement('h3');
			h3.textContent = 'Dealers hand' ; 
			document.body.appendChild(h3);
			data.forEach(function(val,ind,arr){
				var cardSuit = document.createElement('h3');
				cardSuit.textContent = (val.rank + ' of ' + val.suit) ;
				document.body.appendChild(cardSuit);
			})
		} else if (xhr1.readyState === 4 && xhr1.status >= 400) {
			console.error('ERROR!!!!');
		}
	};
	xhr1.send(null);
}// end of the initial dealing of cards

var getCardImage = function(card){
	
	switch (card.suit) {
	case value:
		
		break;

	default:
		break;
	}
}
