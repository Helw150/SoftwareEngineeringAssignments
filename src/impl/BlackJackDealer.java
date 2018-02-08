package impl;

import java.util.ArrayList;
import java.util.Collections;

import api.Card;
import api.Hand;
import api.Player;
import api.Dealer;

import impl.GenericBlackJackPlayer;

// Safe gambler - This player avoids busting and conserves money
public class BlackJackDealer extends GenericBlackJackPlayer implements Dealer {
    ArrayList<Card> deck;
    
    public BlackJackDealer(){
	this(1);
    }

    public BlackJackDealer(int num_decks){
	super();
	this.deck = new ArrayList<Card>();
	for (int i=0; i< num_decks; i++){
	    for (Card.Value value : Card.Value.values()){
		for (Card.Suit suit : Card.Suit.values()){		
		    this.deck.add(new Card(value, suit));
		}
	    }
	}
    }
    
    public int placeWager(){
	return 0;
    }
    
    public boolean requestCard(){
	return false;
    }

    public void dealCard(Player player){
	Card card = this.deck.remove(0);
	player.receive(card);
    }

    public void collectCards(Player player){
	Hand relinquished_hand = player.relinquishCards();
	this.deck.addAll(relinquished_hand.getCards());
	this.shuffle();
    }

    public void shuffle(){
	Collections.shuffle(this.deck);
    }
}

