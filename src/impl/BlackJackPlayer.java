package impl;

import api.Hand;
import api.Player;

import impl.GenericBlackJackPlayer;

// Dumb&Timid - Bets with an individually stupid strategy and is timid on the whole
public class BlackJackPlayer extends GenericBlackJackPlayer {

    public BlackJackPlayer(){
	super();
    }

    public BlackJackPlayer(String name){
	super(name);	
    }
    
    public BlackJackPlayer(String name, int initial_balance){
	super(name, initial_balance);
    }
    // Timid
    public int placeWager(){
	int wager = this.balance/10;
	return super.placeWager(wager);
    }
    // Dumb
    public boolean requestCard(){
	return (!this.player_hand.isWinner() && this.player_hand.isValid());
    }
} 
