package impl;

import api.Hand;
import api.Player;

import impl.GenericBlackJackPlayer;

// Safe gambler - This player avoids busting and conserves money
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
	int wager = this.balance;
	return super.placeWager(wager);
    }
    
    public boolean requestCard(){
	return true;
    }
} 
