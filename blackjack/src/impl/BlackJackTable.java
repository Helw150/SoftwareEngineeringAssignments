package impl;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Collection;
import java.util.HashMap;

import impl.BlackJackPlayer;
import impl.AnotherBlackJackPlayer;
import impl.BlackJackDealer;

import api.Player;
import api.Table;

public class BlackJackTable extends Table {
    private ArrayList<Player> players;
    private BlackJackDealer dealer;
	
    public BlackJackTable(){
	this(2,1);
    }
    
    // Initialize all variables and make every other player a different strategy
    public BlackJackTable (int num_players, int num_decks) {
	this.dealer = new BlackJackDealer(num_decks);
	this.wagers = new HashMap<Player, Integer>();
	this.players = new ArrayList<Player>();
	
	Random rand = new Random();
	int random_money;
	for (int i=0; i < num_players; i++){
	   random_money = rand.nextInt(15) + 5;
	   Player new_player;
	   if (i%2==0) {
	       new_player = new BlackJackPlayer(String.valueOf(i), random_money);
	   }
	   else {
	       new_player = new AnotherBlackJackPlayer(String.valueOf(i), random_money);
	   }
	   this.players.add(new_player);
	   this.wagers.put(new_player, 0);
	}
    }

    // Return read only players list
    public Collection<Player> getPlayers(){
	return Collections.unmodifiableList(this.players);
    }

    // Check if the amount of money the players have is non-zero
    public boolean isGameOver(){
	int total_money_left = 0;
	for (Player player : this.players){
	    total_money_left += player.getMoney();
	}
        return (total_money_left == 0);
    }

    // Use the string helper to construct a complete string
    public String toString(){
	StringBuffer representation = new StringBuffer("");
	representation.append(this.dealer.toString());
	for (Player player : this.players){
	    representation.append(player.toString());
	}
	return representation.toString();
    }

    /* BEGIN SECTION OF DEALER THEN ALL PLAYERS HIGHER ORDER FUNCTION */
    public void collectCards(){
	this.dealer.collectCards(this.dealer);
	for (Player player : this.players){
	    this.dealer.collectCards(player);
	}
    }
    
    public void dealTable(){
	for (int i = 0; i < 2; i++) {
	    this.dealer.dealCard(this.dealer);
	    for (Player player : this.players){
		this.dealer.dealCard(player);
	    }
	}
    }
    /* END HIGHER ORDER SECTION*/
    public void collectBets(){
	for (Player player : this.players){
	    this.wagers.put(player, player.placeWager());
	}
    }

    // Keep dealing as long as player says hit me
    public void playerTurns(){
	while(this.dealer.requestCard()){
	    this.dealer.dealCard(this.dealer);
	}
	for (Player player : this.players){
	    while(player.requestCard()){
		this.dealer.dealCard(player);
	    }
	}
    }

    public void playerEvaluations(){
	Player best_player = this.dealer;
	int best_value = this.dealer.getHand().valueOf();
	for (Player player : this.players){
	    int player_value = player.getHand().valueOf();
	    // Nasty check to assure that the best hand isnt busted, is higher value, or uses less cards to win
	    if(!(best_player.getHand().isValid()) || (player.getHand().isValid() && player_value >= best_value && player.numberOfCards() < best_player.numberOfCards())){
		best_value = player_value;
		best_player = player;
	    }
	}
	int winnings = 0;
	for (Player player : this.players) {
	    winnings += wagers.get(player);
	}
	best_player.payOut(winnings);
    }
    
}
