package data;


public class Card implements Comparable<Card>{
	private Deck.Rank rank;
	private Deck.Suit suit;
	
	public Card(Deck.Rank r, Deck.Suit s){
		rank = r;
		suit = s;
	}
	
	public Deck.Rank getRank(){
		return rank;
	}
	public void setRank(Deck.Rank rank) {
		this.rank = rank;
	}

	public Deck.Suit getSuit() {
		return suit;
	}

	public void setSuit(Deck.Suit suit) {
		this.suit = suit;
	}

	@Override
	public String toString() {
		return (rank + " of " + suit).toLowerCase();
	}
	
	public int compareTo(Card card) {
		int compareRank = this.rank.compareTo(card.rank);
		int compareSuit = this.suit.compareTo(card.suit);

		if (compareRank == 0)
			return compareSuit;
		else
			return compareRank;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}
}
