package impl;

import api.Hand;
import api.Player;

import impl.GenericBlackJackPlayer;

// Safe gambler - This player avoids busting and conserves money
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
    
    public int placeWager(){
	int wager = this.balance;
	return super.placeWager(wager);
    }
    
    public boolean requestCard(){
	return true;
    }
} 
