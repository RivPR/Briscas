package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import data.Deck.Rank;
import data.Deck.Suit;
import entities.HighScores;

@Transactional
public abstract class BriscasDBDAO implements BriscasDAO {
	private Suit life;
	private Deck gameDeck;
 
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Deck getDeck() {
		Deck deck = new Deck();
		deck.createDeck();
		this.gameDeck = deck;
		return deck;
	}

	@Override
	public int getPoints(Card card) {
		Rank rank = card.getRank();
		int value = 0;
		switch (rank) {
		case ONE:
			value = 11;
			System.out.println("Value of: " + value);
			break;
		case THREE:
			value = 10;
			System.out.println("Value of: " + value);
			break;
		case TEN:
			value = 2;
			System.out.println("Value of: " + value);
			break;
		case ELEVEN:
			value = 3;
			System.out.println("Value of: " + value);
			break;
		case TWELVE:
			value = 4;
			System.out.println("Value of: " + value);
			break;
		default:
			System.out.println("no value");
			break;
		}
		return value;
	}

	@Override
	public boolean checkLife(Suit suit) {
		if(suit == life){
			return true;								
		}
		else{
			return false;
		}
	}

	@Override
	public List<HighScores> getTopTen() {
		List<HighScores> hs = em.createQuery("SELECT * FROM HighScores ORDER BY score DESC LIMIT 10", HighScores.class).getResultList();
		return hs;
	}
	
	@Override
	public Card drawCard(Deck gameDeck){
		Card card = gameDeck.deck.get(0);
		gameDeck.deck.remove(0);
		return card;
	}
	
	@Override
	public int getValue(Card card){
		Rank value = card.getRank();
		int valueNum=0;
		switch (value) {
		case ONE:
			valueNum = 14;
			System.out.println("Value of: " + valueNum);
			break;
		case TWO:
			valueNum = 2;
			System.out.println("Value of: " + valueNum);
			break;
		case THREE:
			valueNum = 13;
			System.out.println("Value of: " + valueNum);
			break;
		case FOUR:
			valueNum = 4;
			System.out.println("Value of: " + valueNum);
			break;
		case FIVE:
			valueNum = 5;
			System.out.println("Value of: " + valueNum);
			break;
		case SIX:
			valueNum = 6;
			System.out.println("Value of: " + valueNum);
			break;
		case SEVEN:
			valueNum = 7;
			System.out.println("Value of: " + valueNum);
			break;
		case TEN:
			valueNum = 10;
			System.out.println("Value of: " + valueNum);
			break;
		case ELEVEN:
			valueNum = 11;
			System.out.println("Value of: " + valueNum);
			break;
		case TWELVE:
			valueNum = 12;
			System.out.println("Value of: " + valueNum);
			break;
		default:
			System.out.println("no value");
			break;
		}
		return valueNum;
	}
	@Override
	public boolean checkWhoHasHigher(Card card1, Card card2){
		boolean result = false;
		if (card1.getSuit() == life && card2.getSuit() != life){
			result = (getValue(card1)>getValue(card2))? true:false;
			return result;
		} else if(card1.getSuit() != life && card2.getSuit() != life){
			result =(getValue(card1)>getValue(card2))? true:false;
			return result;
		} else if(card1.getSuit() != life && card2.getSuit() == life){
			return result = false;
		}
		return result;
	}
	
	public Suit getLife() {
		return life;
	}

	public void setLife(Suit life) {
		this.life = life;
	}
	
	//AI process
	
	

}
