package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import data.Deck.Rank;
import data.Deck.Suit;
import entities.HighScores;

@Transactional
public class BriscasDBDAO implements BriscasDAO {
	private Suit life;
	private Card lifeCard;
	private Deck gameDeck;
	private ArrayList<Card> playerHand;
	private ArrayList<Card> dealerHand;
	private ArrayList<Card> playerPile;
	private ArrayList<Card> dealerPile;
	private int playerTotal;
	private int dealerTotal;
	
 
	
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
		System.out.println("this is the value: " + value);
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
	
	public Card getLife() {
		return lifeCard;
	}

	public void setLife(Card life) {
		this.life = life.getSuit();
		this.lifeCard = life;
		
	}
	
	@Override
	public ArrayList<Card> playerHand(Card card1, Card card2, Card card3){
		
		playerHand = new ArrayList<Card>();
		playerHand.add(0, card1);
		playerHand.add(1, card2);
		playerHand.add(2, card3);
		return playerHand;
	}
	@Override
	public ArrayList<Card> dealerHand(Card card1, Card card2, Card card3){
		dealerHand = new ArrayList<Card>();
		dealerHand.add(0, card1);
		dealerHand.add(1, card2);
		dealerHand.add(2, card3);
		return dealerHand;
	}
	//adds the total of point of the hands played
	@Override
	public void totalPointsHand(Card card1, Card card2){
		boolean result = checkWhoHasHigher(card1, card2);
		if(result==true){
			this.dealerTotal = ( getPoints(card1) + getPoints(card2) );
			dealerPile.add(card1);
			dealerPile.add(card2);
		}else if(result==false){
			this.playerTotal = ( getPoints(card1) + getPoints(card2) );
			playerPile.add(card1);
			playerPile.add(card2);
		}
		
	}
	
	//AI process
	@Override
	public Card throwLowestCard(){
		ArrayList<Card> cards = dealerHand;
		int number = 15;
		Card cardToPlay = new Card();
			for(int i = 0; i < cards.size(); i++) {
				if(cards.get(i).getSuit()==life){
					if(number > (getValue(cards.get(i)) + 12) ) {
						number = getValue(cards.get(i));
						cardToPlay = cards.get(i);
					}
				}else if(cards.get(i).getSuit() != life){
					if(number > getValue(cards.get(i)) ) {
						number = getValue(cards.get(i));
						cardToPlay = cards.get(i);
					}
					
				}
			}
			return cardToPlay;		
	}
	@Override
	public Card throwLifeOrHigh(){
		ArrayList<Card> cards = dealerHand;
		Card cardtoPlay = new Card();
		int number = 15;
			for(int i = 0 ; i<cards.size();i++){
				if(cards.get(i).getSuit()==life){
					if(number > (getValue(cards.get(i)) + 12) ) {
						number = getValue(cards.get(i));
						cardtoPlay = cards.get(i);
					}
				}else if(cards.get(i).getSuit() != life){
					if(number < getValue(cards.get(i)) ) {
						number = getValue(cards.get(i));
						cardtoPlay = cards.get(i);
					}
					
				}
			}
		
		return cardtoPlay;
	}
	//method checks if the card on play has any value
	@Override
	public boolean checkPointValueOfPlayedCard(Card card){
		boolean result = false;
		if(getPoints(card) >= 2){
			result = true;
		}
		return result;
	}

	public Deck getGameDeck() {
		return gameDeck;
	}

	public void setGameDeck(Deck gameDeck) {
		this.gameDeck = gameDeck;
	}

	public ArrayList<Card> getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(ArrayList<Card> playerHand) {
		this.playerHand = playerHand;
	}

	public ArrayList<Card> getDealerHand() {
		return dealerHand;
	}

	public void setDealerHand(ArrayList<Card> dealerHand) {
		this.dealerHand = dealerHand;
	}

	public ArrayList<Card> getPlayerPile() {
		return playerPile;
	}

	public void setPlayerPile(ArrayList<Card> playerPile) {
		this.playerPile = playerPile;
	}

	public ArrayList<Card> getDealerPile() {
		return dealerPile;
	}

	public void setDealerPile(ArrayList<Card> dealerPile) {
		this.dealerPile = dealerPile;
	}

	public int getPlayerTotal() {
		return playerTotal;
	}

	public void setPlayerTotal(int playerTotal) {
		this.playerTotal = playerTotal;
	}

	public int getDealerTotal() {
		return dealerTotal;
	}

	public void setDealerTotal(int dealerTotal) {
		this.dealerTotal = dealerTotal;
	}





	

}
