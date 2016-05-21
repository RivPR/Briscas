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
public class BriscasDBDAO implements BriscasDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Deck getDeck() {
		Deck deck = new Deck();
		deck.createDeck();
		return deck;
	}

	@Override
	public int getValue(Card card) {
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
	public Suit getLife(Suit suit) {
		
		return null;					
	}

	@Override
	public List<HighScores> hs() {
		List<HighScores> hs = em.createQuery("SELECT * FROM HighScores ORDER BY score DESC LIMIT 10", HighScores.class).getResultList();
		return hs;
	}

}
