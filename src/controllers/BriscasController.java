package controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		setGameLife(card4);
		gameDeck.deck.remove(0);
		ArrayList<Card> playerHand = dao.playerHand(card1, card2, card3);
		dao.setGameDeck(gameDeck);
		return playerHand;
	}

	public void setGameLife(Card c){
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
	@RequestMapping("playCard/{number}")
	public String playCard(@PathVariable("number") Integer number){
		String str2 = "nobody";
		Card cardPlayed = dao.getPlayerHand().get(number);
		System.out.println(cardPlayed);
		ArrayList<Card> al = new ArrayList<>();
		
		if(dao.checkLife(cardPlayed.getSuit())){
				Card card1 = dao.throwLowestCard();
				boolean bool = dao.checkWhoHasHigher(card1, cardPlayed);
				if(bool == true){
					al.add(cardPlayed);
					al.add(card1);
					dao.setDealerPile(al);
					str2 = "dealer";
				}else if(bool == false){
					al.add(cardPlayed);
					al.add(card1);
					dao.setPlayerPile(al);
					str2 = "player";
				}
		}else if(!dao.checkLife(cardPlayed.getSuit())){
			boolean value = dao.checkPointValueOfPlayedCard(cardPlayed);
			if(value){
				Card card1 = dao.throwLifeOrHigh();
				boolean bool = dao.checkWhoHasHigher(card1, cardPlayed);
				if(bool == true){
					al.add(cardPlayed);
					al.add(card1);
					dao.setDealerPile(al);
					str2 = "dealer";
				}else if(bool == false){
					al.add(cardPlayed);
					al.add(card1);
					dao.setPlayerPile(al);
					str2 = "player";
				}
			
				
			}else if(value == false){
				Card card1 = dao.throwLowestCard();
				boolean bool = dao.checkWhoHasHigher(card1, cardPlayed);
				if(bool == true){
					al.add(cardPlayed);
					al.add(card1);
					dao.setDealerPile(al);
					str2 = "dealer";
				}else if(bool == false){
					al.add(cardPlayed);
					al.add(card1);
					dao.setPlayerPile(al);
					str2 = "player";
				}
			}
		}
		String str = "This hand was won by: " + str2;
		
		return str;
		
	}
	
}
