package test.unit;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

import api.Dealer;
import api.Player;
import impl.BlackJackPlayer;
import impl.BlackJackDealer;

public class DealerTest {
    private Dealer dealer;
    private BlackJackPlayer player;

    @Before
    public void setup() {
        player = new BlackJackPlayer("Player 1");
        dealer = new BlackJackDealer(1);
    }

    @Test
    public void testDealCardGoesToPlayer() {
        int initialSize = player.numberOfCards();
        dealer.dealCard(player);
        int dealtSize = player.numberOfCards();

        assertEquals(dealtSize, initialSize + 1);
    }

    @Test
    public void testCardCollectionEmptiesHand() {
        dealer.dealCard(player);
        dealer.collectCards(player);

        assertEquals(player.numberOfCards(), 0);
    }

    @Test
    public void testDealerCanDealToDealer() {
        dealer.dealCard((Player)dealer);

        assertEquals(dealer.getHand().getCards().size(), 1);
    }
}
