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
public class PlayerInitializationTest {
    private static final int MONEY = 10;

    private Player player;

    public PlayerInitializationTest(Player player) {
        this.player = player;
    }

    @Parameters
    public static Iterable<Player> parameters() {
        String name = "Player 1";

        return Arrays.asList(new BlackJackPlayer(name, MONEY),
                             new AnotherBlackJackPlayer(name, MONEY));
    }

    @Test
    public void testConstructorSetsMoney() {
        assertEquals(player.getMoney(), MONEY);
    }

    @Test
    public void testNoCardsInitially() {
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
