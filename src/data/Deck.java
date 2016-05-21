package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	public ArrayList<Card> deck;
	
	public void createDeck(){
		deck = new ArrayList<>(40);
		
		for (Suit s : Suit.values()){
			for (Rank r : Rank.values()){
				deck.add(new Card(r, s));
			}
		}
		Collections.shuffle(deck);
		System.out.println(deck.toString());
	}
	
	public enum Rank {
		ONE(11), TWO(0), THREE(10), FOUR(0), FIVE(0), SIX(0), SEVEN(0), TEN(2), ELEVEN(3), TWELVE(4);
		private int numVal;
		
		Rank(int numVal){
			this.numVal = numVal;
		}
		
		public int getNumVal(){
			return numVal;
		}
	}
	
	public enum Suit {
		CHALICE, SPADE, CLUB, COIN
	}
}
