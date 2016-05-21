package data;

import java.util.ArrayList;
import java.util.List;

import data.Deck.Suit;
import entities.HighScores;

public interface BriscasDAO {

	public Deck getDeck();
	
	public int getValue(Card card);
	
	public Deck.Suit getLife(Deck.Suit suit);
	
	public List<HighScores> getTopTen();

	boolean checkLife(Suit suit);

	Card drawCard(Deck gameDeck);

	boolean checkHigher(Card card1, Card card2);

	boolean checkWhoHasHigher(Card card1, Card card2);

	int getPoints(Card card);
	
}
