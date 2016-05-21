package data;

import java.util.ArrayList;
import java.util.List;

import entities.HighScores;

public interface BriscasDAO {

	public Deck getDeck();
	
	public int getValue(Card card);
	
	public Deck.Suit getLife(Deck.Suit suit);
	
	public List<HighScores> hs();
	
}
