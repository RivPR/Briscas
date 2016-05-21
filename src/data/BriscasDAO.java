package data;

import java.util.ArrayList;
import entities.HighScores;

public interface BriscasDAO {

	public Deck getDeck();
	
	public int getValue(Card card);
	
	public Deck.Suit getLife(Deck.Suit suit);
	
	public ArrayList<HighScores> hs();
	
}
