addEventListener('load', function() {
	console.log('LOADED!');
	var startButton = document.getElementById('startButton');
	startButton.addEventListener('click', getInitialCards);

});

var getInitialCards = function(e){
	e.preventDefault();
	//get cards for player
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'rest/dealPlayer');
	xhr.onreadystatechange = function() {
		if (xhr.status < 400 && xhr.readyState === 4) {
			// convert responseText to JSON
			var playerCard = [];
			var data = JSON.parse(xhr.responseText);	
			var h3 = document.createElement('h2');
			h3.id = 'dealers';
			h3.textContent = 'Players hand' ; 
			document.body.appendChild(h3);
			data.forEach(function(val,ind,arr){
				var cardSuit = document.createElement('h3');
				cardSuit.textContent = (val.rank + ' of ' + val.suit) ;
				var cardImageph = getCardImage(val);
				var cardImage = document.createElement('IMG');
				cardImage.src = (cardImageph);
				playerCard.push(cardImage);
				cardsToCanvas(cardImageph, val);
				//to display cards to browser
				document.body.appendChild(cardImage);
				document.body.appendChild(cardSuit);
			})
		} else if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error('ERROR!!!!');
		}
	};
	xhr.send(null);
	//get cards for dealer
	var xhr1 = new XMLHttpRequest();
	xhr1.open('GET', 'rest/dealDealer');
	xhr1.onreadystatechange = function() {
		if (xhr1.status < 400 && xhr1.readyState === 4) {
			var dealerCard = [];
			// convert responseText to JSON
			var data = JSON.parse(xhr1.responseText);
			
			var h3 = document.createElement('h2');
			h3.textContent = 'Dealers hand' ; 
			h3.id = 'dealers';
			document.body.appendChild(h3);
			data.forEach(function(val,ind,arr){
				var cardSuit = document.createElement('h3');
				cardSuit.textContent = (val.rank + ' of ' + val.suit) ;
				var cardImageph = getCardImage(val);
				var cardImage = document.createElement('IMG');
				cardImage.src = (cardImageph);
				dealerCard.push(cardImage);
//				cardsToCanvas(cardImageph);
				
				//to display cards to browser
				document.body.appendChild(cardImage);
				document.body.appendChild(cardSuit);
			})
		} else if (xhr1.readyState === 4 && xhr1.status >= 400) {
			console.error('ERROR!!!!');
		}
	};
	//delete start button
	
	xhr1.send(null);
	//erase the start button
	var eraseButton = document.getElementById('startDiv');
	while (eraseButton.hasChildNodes()) {
		eraseButton.removeChild(eraseButton.lastChild);
	}
}// end of the initial dealing of cards
var counter = 250;
var cardsToCanvas = function(name, card){

	var cx = document.getElementById('briscasCanvas').getContext('2d');
	var img = document.createElement('img');
	img.src = name;
	console.log(name);
	var spriteW = 250, spriteH = 350;
	
		if(counter === 250){
			console.log('in if draw ' + counter);
			img.addEventListener('load', function(){
			cx.drawImage(img, counter, 398, spriteW, spriteH);
			nameOfCards(card, counter);
			counter += 255;
			console.log('in if 2 draw ' + counter);
			});
		}
		else{
			console.log('in else draw ' + counter);
				cx.drawImage(img, counter, 398, spriteW, spriteH);
				nameOfCards(card, counter);
				counter += 255;
		}
		//cx.drawImage(img, counter, 10, spriteW, spriteH);
	
};

var nameOfCards = function(cardName, counter){
	var cx=document.getElementById('briscasCanvas').getContext('2d');

	cx.font="20px Georgia";
	cx.fillText((cardName.rank + ' of ' + cardName.suit),(counter+35),800);


	// Create gradient
//	var gradient=ctx.createLinearGradient(0,0,c.width,0);
//	gradient.addColorStop("0","black");
//	gradient.addColorStop("0.5","blue");
//	gradient.addColorStop("1.0","red");
//	// Fill with gradient
//	cx.fillStyle=gradient;
//	cx.fillText(cardName,10,90);
}

//get card image, I kept the names of the cards consistent just so i would be able to pair them easily with their text counterpart
var getCardImage = function(card){
	var number = '1';
	var name;
	do{
		switch(card.rank){
			case 'ONE':
				number='1';
			break;
			case 'TWO':
				number='2';
				break;
			case 'THREE':
				number='3';
				break;
			case 'FOUR':
				number='4';
				break;
			case 'FIVE':
				number='5';
				break;
			case 'SIX':
				number='6';
				break;
			case 'SEVEN':
				number='7';
				break;
			case 'TEN':
				number='10';
				break;
			case 'ELEVEN':
				number='11';
				break;
			case 'TWELVE':
				number='12';
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
	}while(false);
	return name;
}


