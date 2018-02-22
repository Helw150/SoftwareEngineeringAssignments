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
	super("Dealer");
	this.deck = new ArrayList<Card>();
	// Generate a deck with every possible card and do so for the number of decks
	for (int i=0; i< num_decks; i++){
	    for (Card.Value value : Card.Value.values()){
		for (Card.Suit suit : Card.Suit.values()){		
		    this.deck.add(new Card(value, suit));
		}
	    }
	}
	// Shuffle the deck before playing
	this.shuffle();
    }
    
    public int placeWager(){
	return 0;
    }
    
    public boolean requestCard(){
	return(this.player_hand.valueOf() < 17);
    }

    // Move cards from deck to player
    public void dealCard(Player player){
	Card card = this.deck.remove(0);
	player.receive(card);
    }

    // Move cards from player to deck
    public void collectCards(Player player){
	Hand relinquished_hand = player.relinquishCards();
	this.deck.addAll(relinquished_hand.getCards());
	this.shuffle();
    }

    // Use built in shuffle function
    public void shuffle(){
	Collections.shuffle(this.deck);
    }
}

