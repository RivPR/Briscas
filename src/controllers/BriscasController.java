package controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		ArrayList<Card> playerHand = dao.playerHand(card1, card2, card3);
		return playerHand;
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
		return dealerHand;
	}
}
