package test.unit.wbh230;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import api.Game;
import api.Chip;
import impl.game.Complica;
import impl.game.TicTacToe;
import impl.game.ConnectFour;

import exc.GameStateException;
import exc.GameIndexOutOfBoundsException;


/**
 * An example of how to use JUnit Parameters to test common elements
 * between games. Characteristics that are unique -- winning
 * conditions, for example -- will probably require specialised tests;
 * however, a lot of testing mileage can be gained, with minimal
 * coding effort, by identifying such "common" requirements.
 **/

@RunWith(Parameterized.class)
public class CommonTest {
    private Game game;

    public CommonTest(Game game) {
        this.game = game;
    }

    @Parameters
    public static Iterable<Game> parameters() {
        return Arrays.asList(new ConnectFour(),
                             new Complica(),
                             new TicTacToe());
    }

        @Test(expected = GameIndexOutOfBoundsException.class)
    public void placeColTooSmall() throws Exception {
        game.placeChip(0, -1);
    }

    @Test(expected = GameIndexOutOfBoundsException.class)
    public void placeColTooLarge() throws Exception {
        game.placeChip(0, 10);
    }

    @Test(expected = GameIndexOutOfBoundsException.class)
    public void getColTooSmall() throws Exception {
        game.getChip(0, -1);
    }

    @Test(expected = GameIndexOutOfBoundsException.class)
    public void getColTooLarge() throws Exception {
        game.getChip(0, 10);
    }

    @Test(expected = GameIndexOutOfBoundsException.class)
    public void getRowTooSmall() throws Exception {
        game.getChip(-1, 0);
    }

    @Test(expected = GameIndexOutOfBoundsException.class)
    public void getRowTooLarge() throws Exception {
        game.getChip(10, 0);
    }

    @Test
    public void getAnySpot() throws Exception {
	int count = 0;
	for (int i = 0; i < game.getRows(); i++) {
	    for (int j = 0; j < game.getColumns(); j++) {
		game.getChip(i, j);
		count++;
	    }
	}
	assertEquals(game.getRows()*game.getColumns(), count);
    }
    
    @Test
    public void startPlayerNotEmpty() {
	assertTrue(game.getCurrentPlayer() == Chip.RED || game.getCurrentPlayer() == Chip.BLUE);
    }

    @Test
    public void startNoWinning() throws GameStateException {
	assertFalse(game.isGameOver());
    }
    
    
    @Test(expected = GameStateException.class)
    public void startNoWinner() throws GameStateException {
	assertTrue(game.getWinningPlayer() != Chip.RED || game.getWinningPlayer() != Chip.BLUE);
    }

}
