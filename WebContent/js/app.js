
addEventListener('load', function() {
	console.log('LOADED!');
	var startButton = document.getElementById('startButton');
	startButton.addEventListener('click', getInitialCards);
	

});

var getInitialCards = function(e) {
	e.preventDefault();

	// get cards for player
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'rest/dealPlayer');
	xhr.onreadystatechange = function() {
		if (xhr.status < 400 && xhr.readyState === 4) {
			// convert responseText to JSON
			var playerCard = [];
			var data = JSON.parse(xhr.responseText);
			var h3 = document.createElement('h2');
			h3.id = 'dealers';
			h3.textContent = 'Players hand';
			document.body.appendChild(h3);
			data.forEach(function(val, ind, arr) {
				var cardSuit = document.createElement('h3');
				cardSuit.textContent = (val.rank + ' of ' + val.suit);
				var cardImageph = getCardImage(val);
				var cardImage = document.createElement('IMG');
				cardImage.src = (cardImageph);
				playerCard.push(cardImage);
				cardsToCanvasPlayer(cardImageph, val);
				// to display cards to browser
				document.body.appendChild(cardImage);
				document.body.appendChild(cardSuit);
			})
		} else if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error('ERROR!!!!');
		}
	};
	xhr.send(null);
	// get cards for dealer
	var xhr1 = new XMLHttpRequest();
	xhr1.open('GET', 'rest/dealDealer');
	xhr1.onreadystatechange = function() {
		if (xhr1.status < 400 && xhr1.readyState === 4) {
			var dealerCard = [];
			// convert responseText to JSON
			var data = JSON.parse(xhr1.responseText);

			var h3 = document.createElement('h2');
			h3.textContent = 'Dealers hand';
			h3.id = 'dealers';
			document.body.appendChild(h3);
			data.forEach(function(val, ind, arr) {
				var cardSuit = document.createElement('h3');
				cardSuit.textContent = (val.rank + ' of ' + val.suit);
				var cardImageph = getCardImage(val);
				var cardImage = document.createElement('IMG');
				cardImage.src = (cardImageph);
				dealerCard.push(cardImage);
				cardsToCanvasDealer(cardImageph, val);

				// to display cards to browser
				// document.body.appendChild(cardImage);
				// document.body.appendChild(cardSuit);
				
				
			})

		} else if (xhr1.readyState === 4 && xhr1.status >= 400) {
			console.error('ERROR!!!!');
		}
	};
	// delete start button

	xhr1.send(null);
	// erase the start button
	var eraseButton = document.getElementById('startDiv');
	while (eraseButton.hasChildNodes()) {
		eraseButton.removeChild(eraseButton.lastChild);
	}

	lifeToCanvas();
}// end of the initial dealing of cards

var lifeToCanvas = function() {
	var xhr3 = new XMLHttpRequest();
	xhr3.open('GET', 'rest/getLife');
	xhr3.onreadystatechange = function() {
		if (xhr3.status < 400 && xhr3.readyState === 4) {

			// convert responseText to JSON
			var data = JSON.parse(xhr3.responseText);

			var cardImageph = getCardImage(data);
			var cardImage = document.createElement('IMG');
			cardImage.src = (cardImageph);

			cardsToCanvasLife(cardImageph, data);

			// to display cards to browser
			// document.body.appendChild(cardImage);
			// document.body.appendChild(cardSuit);

		} else if (xhr3.readyState === 4 && xhr3.status >= 400) {
			console.error('ERROR!!!!');
		}
	};
	// delete start button

	xhr3.send(null);
};

var cardsToCanvasLife = function(name, card) {

	var cx = document.getElementById('briscasCanvas').getContext('2d');
	var img = document.createElement('img');
	img.src = name;
	// console.log(name);
	var spriteW = 200, spriteH = 250;

	// console.log('in if draw ' + counter);
	img.addEventListener('load', function() {
		cx.drawImage(img, 10, 200, spriteW, spriteH);
		cx.strokeStyle = '#f000000';
		cx.lineWidth = 15;
		cx.strokeRect(10, 200, spriteW, spriteH);
		//console.log((card.rank + ' of ' + card.suit));
		cx.fillText(('Life'), 95, 185);
		cx.fillText((card.rank + ' of ' + card.suit), 25, 490);

		// console.log('in if 2 draw ' + counter);
	});

	// cx.drawImage(img, counter, 10, spriteW, spriteH);

};

var counter = 250;
var counter2 = 955;
var cardsToCanvasPlayer = function(name, card) {

	var cx = document.getElementById('briscasCanvas').getContext('2d');
	var img = document.createElement('img');
	img.src = name;
	// console.log(name);
	var spriteW = 250, spriteH = 350;

	if (counter === 250) {
		// console.log('in if draw ' + counter);
		img.addEventListener('load', function() {
			cx.drawImage(img, counter, 418, spriteW, spriteH);
			cx.strokeStyle = '#f000000';
			cx.lineWidth = 15;
			cx.strokeRect(counter, 418, spriteW, spriteH);
			nameOfCards(card, counter);
			counter += 270;
			// console.log('in if 2 draw ' + counter);
		});
	} else {
		// console.log('in else draw ' + counter);
		cx.drawImage(img, counter, 418, spriteW, spriteH);
		cx.strokeStyle = '#f000000';
		cx.lineWidth = 15;
		cx.strokeRect(counter, 418, spriteW, spriteH);
		nameOfCards(card, counter);
		counter += 270;
	}
	// cx.drawImage(img, counter, 10, spriteW, spriteH);

};

var cardsToCanvasDealer = function(name, card) {
	var cx = document.getElementById('briscasCanvas').getContext('2d');
	var img = document.createElement('img');
	img.src = 'img/back.png';
	// console.log(name);
	var spriteW = 100, spriteH = 200;

	if (counter2 === 250) {
		// console.log('in if draw ' + counter2);
		img.addEventListener('load', function() {
			cx.drawImage(img, counter2, 10, spriteW, spriteH);

			counter2 += 105;
			// console.log('in if 2 draw ' + counter2);
		});
	} else {
		// console.log('in else draw ' + counter2);
		cx.drawImage(img, counter2, 10, spriteW, spriteH);
		counter2 += 105;
	}
	//\ cx.drawImage(img, counter, 10, spriteW, spriteH);
}

window.onload = function() {
	draw();
}

window.onclick = function(e) {
	if (e.pageX < 1066 && e.pageY < 953) {
		var cX = Math.floor(e.pageX / (810 / 3));
		var cY = Math.floor(e.pageY / (367));

		var alreadyClicked = false;

		console.log('here ' + cX, cY);

		if ((1 == cX) && (2 == cY || 1 == cY)) {

			console.log('first card');
		} else if ((2 == cX) && (2 == cY || 1 == cY)) {

			console.log('second card');
		} else if ((3 == cX) && (2 == cY || 1 == cY)) {

			console.log('third card');
		}

	}
}

function draw() {

}

var nameOfCards = function(cardName, counter) {
	var cx = document.getElementById('briscasCanvas').getContext('2d');

	cx.font = "20px Georgia";
	cx.fillText((cardName.rank + ' of ' + cardName.suit), (counter + 55), 820);

}

// get card image, I kept the names of the cards consistent just so i would be
// able to pair them easily with their text counterpart
var getCardImage = function(card) {
	var number = '1';
	var name;
	do {
		switch (card.rank) {
		case 'ONE':
			number = '1';
			break;
		case 'TWO':
			number = '2';
			break;
		case 'THREE':
			number = '3';
			break;
		case 'FOUR':
			number = '4';
			break;
		case 'FIVE':
			number = '5';
			break;
		case 'SIX':
			number = '6';
			break;
		case 'SEVEN':
			number = '7';
			break;
		case 'TEN':
			number = '10';
			break;
		case 'ELEVEN':
			number = '11';
			break;
		case 'TWELVE':
			number = '12';
			break;
		}
		switch (card.suit) {

		case 'SPADE':
			name = ('img/spades' + number + '.jpg');
			break;

		case 'CHALICE':
			name = ('img/cups' + number + '.jpg');
			break;

		case 'CLUB':
			name = ('img/blunt' + number + '.jpg');
			break;

		case 'COIN':
			name = ('img/coins' + number + '.jpg');
			break;
		}
	} while (false);
	return name;
}

var postData = function(method, url, callback, object) {
	var xhr = new XMLHttpRequest();
	xhr.open(method, url);

	if (object) {
		xhr.setRequestHeader('Content-type', 'application/json');
		object = JSON.stringify(object);
	} else {
		object = null;
	}

	xhr.onreadystatechange = function() {
		callback(xhr);
	}

	xhr.send(object);
};
