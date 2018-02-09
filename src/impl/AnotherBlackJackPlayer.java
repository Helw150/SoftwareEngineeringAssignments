package impl;

import api.Hand;
import api.Player;

import impl.GenericBlackJackPlayer;

// Smart&Bold - This gambler bets alot, but follows a best practice strategy.
public class AnotherBlackJackPlayer extends GenericBlackJackPlayer {

    public AnotherBlackJackPlayer(){
	super();
    }

    public AnotherBlackJackPlayer(String name){
	super(name);	
    }
    
    public AnotherBlackJackPlayer(String name, int initial_balance){
	super(name, initial_balance);
    }
    
    public int placeWager(){
	int wager = (3*this.balance)/4;
	return super.placeWager(wager);
    }
    
    public boolean requestCard(){
	return (this.player_hand.valueOf() <= 16);
    }
} 
