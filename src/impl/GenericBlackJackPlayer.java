package impl;

import api.Card;
import api.Hand;
import api.Player;

public abstract class GenericBlackJackPlayer implements Player {
    protected Hand player_hand;
    private String name;
    protected int balance;
    private int wins;
    
    public GenericBlackJackPlayer(){
	this("No Name", 0);
    }
    public GenericBlackJackPlayer(String name){
        this(name, 0);
    }

    public GenericBlackJackPlayer(String name, int initial_balance){
        this.player_hand = new BlackJackHand();
	this.name = name;
	this.balance = initial_balance;
    }

    protected int placeWager(int wager){
	if(wager < 1 && this.balance != 0){
	    wager = 1;
	} 
	this.balance -= wager;
	return wager;
    }

    public String toString(){
	return this.name + " - Balance: " + this.balance + " - Wins: "+ this.wins + "\n";
    }
    
    public void receive(Card card){
	this.player_hand.addCard(card);
    }

    public Hand getHand(){
	return this.player_hand;
    }

    public Hand relinquishCards(){
	Hand hand_copy = this.player_hand;
	this.player_hand = new BlackJackHand();
	return hand_copy;
    }

    public void payOut(int money){
	this.balance += money;
	this.wins++;
    }

    public int getMoney(){
	return this.balance;
    }

    public String getName(){
	return this.name;
    }

    public int numberOfCards(){
	return this.player_hand.countCards();
    }

    public int compareTo(Player other_player){
	return this.name.compareTo(other_player.getName());
    }
} 
