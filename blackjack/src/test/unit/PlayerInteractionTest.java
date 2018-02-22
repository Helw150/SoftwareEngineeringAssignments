package test.unit;

import java.util.Set;
import java.util.Arrays;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import api.Card;
import api.Player;
import impl.BlackJackPlayer;
import impl.AnotherBlackJackPlayer;

@RunWith(Parameterized.class)
public class PlayerInteractionTest {
    private static final int MONEY = 10;

    private Player player;

    public PlayerInteractionTest(Player player) {
        this.player = player;
    }

    @Parameters
    public static Iterable<Player> parameters() {
        String name = "Player 1";

        return Arrays.asList(new BlackJackPlayer(name, MONEY),
                             new AnotherBlackJackPlayer(name, MONEY));
    }

    @Test
    public void testCardIsStored() {
        int before = player.numberOfCards();
        player.receive(new Card(Card.Value.ACE, Card.Suit.CLUB));
        int after = player.numberOfCards();

        assertTrue(before < after);
    }

    @Test
    public void testAbilityToReceiveMoney() {
        int wallet = player.getMoney();
        player.payOut(1);

        assert(player.getMoney() > wallet);
    }

    @Test
    public void testWagerDeducts() {
        for (int i = 0; i < MONEY && player.getMoney() > 0; i++) {
            int before = player.getMoney();
            player.placeWager();
            int after = player.getMoney();

            assertTrue(before > after);
        }
    }

    @Test
    public void testWagerWithinMeans() {
        for (int i = 0; i < MONEY && player.getMoney() > 0; i++) {
            int ability = player.getMoney();
            int wager = player.placeWager();
            assertTrue(wager <= ability);
        }
    }

    @Test
    public void testMoneyAlwaysPositive() {
        for (int i = 0; i < MONEY * 2; i++) {
            player.placeWager();
            assertTrue(player.getMoney() >= 0);
        }
    }

    @Test
    public void testRelinquishCards() {
        player.receive(new Card(Card.Value.ACE, Card.Suit.CLUB));
        player.relinquishCards();

        assertEquals(player.numberOfCards(), 0);
    }

    @Test
    public void testPlayersAreComparable() {
        int numberOfPlayers = 10;
        Set<Player> players = new TreeSet<Player>();

        for (int i = 0; i < numberOfPlayers; i++) {
            Player p = new BlackJackPlayer(String.valueOf(i));
            players.add(p);
        }

        assertEquals(players.size(), numberOfPlayers);
    }
}
