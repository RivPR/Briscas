package controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import data.BriscasDBDAO;
import data.Card;
import data.Deck;

@Controller
public class BriscasController {

	@Autowired
	public BriscasDBDAO dao;
	
	//initial dealing of cards
	@ResponseBody
	@RequestMapping("dealPlayer")
	public ArrayList<Card> dealCardsPlayer(){
		Deck gameDeck = dao.getDeck();

		Card card1 = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		Card card2 = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		Card card3 = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		Card card4 = gameDeck.deck.get(0);
		System.out.println("life : " + card4.toString());
		setGameLife(card4);
		gameDeck.deck.remove(0);
		
		
		ArrayList<Card> playerHand = dao.playerHand(card1, card2, card3);
		dao.setGameDeck(gameDeck);
		return playerHand;
	}

	public void setGameLife(Card c){
		System.out.println("in set life");
		System.out.println("this is c " + c.toString());
		dao.setLife(c);
		
	}
	
	@ResponseBody
	@RequestMapping("getLife")
	public Card getLifeCard(){
		Card lifeCard;
		lifeCard = dao.getLife();
		return lifeCard;
	}
	
	@ResponseBody
	@RequestMapping("dealDealer")
	public ArrayList<Card> dealCardsDealer(){
		Deck gameDeck = dao.getDeck();
		Card card1 = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		Card card2 = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		Card card3 = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		ArrayList<Card> dealerHand = dao.dealerHand(card1, card2, card3);
		dao.setGameDeck(gameDeck);
		return dealerHand;
	}
	//play a single card mechanics
	@ResponseBody
	@RequestMapping("playCard")
	public void playCard(int number){
		Card cardPlayed = dao.getDealerHand().get(number);
		
	}
}
