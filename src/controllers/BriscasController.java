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
	private BriscasDBDAO dao;
	
	@ResponseBody
	@RequestMapping("deal")
	public ArrayList<HashMap<Integer, Card>> dealCards(){
		Deck gameDeck = dao.getDeck();
		Card card1 = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		Card card2 = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		Card card3 = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		Card card4 = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		Card card5 = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		Card card6 = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		HashMap<Integer, Card> playerHand = dao.playerHand(card1, card3, card5);
		HashMap<Integer, Card> dealerHand = dao.dealerHand(card2, card4, card6);
		ArrayList<HashMap<Integer, Card>> dealtCards = new ArrayList<>();
		dealtCards.add(playerHand);
		dealtCards.add(dealerHand);
		return dealtCards;
	}
}
