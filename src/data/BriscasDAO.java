package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.Deck.Suit;
import entities.HighScores;

public interface BriscasDAO {

	public Deck getDeck();
	
	public int getValue(Card card);
	
	public Suit getLife();
	
	public List<HighScores> getTopTen();

	boolean checkLife(Suit suit);

	Card drawCard(Deck gameDeck);

	boolean checkWhoHasHigher(Card card1, Card card2);

	int getPoints(Card card);

	Card throwLowestCard(Card card);

	ArrayList<Card> playerHand(Card card1, Card card2, Card card3);

	ArrayList<Card> dealerHand(Card card1, Card card2, Card card3);

	boolean checkPointValueOfPlayedCard(Card card);

	void totalPointsHand(Card card1, Card card2);
	
	//AI Process
	
}
