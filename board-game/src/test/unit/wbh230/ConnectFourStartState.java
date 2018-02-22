package test.unit.wbh230;

import org.junit.Test;
import org.junit.Rule;
import org.junit.Before;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import impl.game.ConnectFour;

import api.Game;
import api.Chip;

import exc.GameStateException;
import exc.GameIndexOutOfBoundsException;

public class ConnectFourStartState {
    private Game game;
    
    @Before
    public void setup() {
	game = new ConnectFour();
    }

    @Test
    public void getColumnsSeven() {
	assertEquals(7, game.getColumns());
    }

    @Test
    public void getRowsSeven() {
	assertEquals(6, game.getRows());
    }

    @Test
    public void emptyBoard() {
	int count = 0;
	for (int i = 0; i < game.getRows(); i++) {
	    for (int j = 0; j < game.getColumns(); j++) {
		if(game.getChip(i,j) != Chip.EMPTY)
		    count++;
	    }
	}
	assertEquals(0, count);
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
