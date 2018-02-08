package impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import api.Card;
import api.Hand;


public class BlackJackHand implements Hand {
    private List<Card> cards_in_hand;
    private int num_cards;
    
    public BlackJackHand() {
	this.cards_in_hand = new ArrayList<Card>();
	this.num_cards = 0;
    }
    
    public void addCard(Card card) {
	this.cards_in_hand.add(card);
	this.num_cards += 1;
    }

    public int countCards() {
	return this.num_cards;
    }
    
    public List<Card> getCards() {
	return Collections.unmodifiableList(this.cards_in_hand);
    }

    private int handleAces(int current_val, int num_aces_in_hand){
	if(current_val >= 21) {
	    // Iterates until either: You didn't bust or You have no more aces that are counted as 11
	    while(current_val > 21 && num_aces_in_hand >= 1){
		current_val -= 10;
		num_aces_in_hand--;
	    }
	}
	return current_val;
    } 
    
    public int valueOf() {
	int value = 0;
	int num_aces = 0;
	for (Card card : this.cards_in_hand) {
	    int current_card_enum = card.getValue().getValue();
	    if(current_card_enum != 1){
		value += current_card_enum;
	    } else {
		value += 11;
		num_aces += 1;
	    }
	}
	return this.handleAces(value, num_aces);
    }

    public int compareTo(Hand other_hand) {
	if(this.valueOf() > other_hand.valueOf()){
	    return 1;
	} else if(this.valueOf() < other_hand.valueOf()){
	    return -1;
	} else {
	    return 0;
	}
    }

    public boolean isValid() {
	return this.valueOf() <= 21;
    }

    public boolean isWinner(){
	return this.valueOf() == 21;
    }
}
