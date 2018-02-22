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

public class ConnectFourEndState {
    private Game game;
    private Chip initial_player;
    
    @Before
    public void setup() throws Exception {
	game = new ConnectFour();
	initial_player = game.getCurrentPlayer();
	for(int i = 0; i < 4; i++){
	    game.placeChip(0,0);
	    if (i < 3)
		game.placeChip(0,1);
	}
    }

    @Test
    public void getGameIsOver() throws Exception {
	assertTrue(game.isGameOver());
    }

    @Test
    public void getCorrectWinner() throws Exception {
	assertEquals(initial_player, game.getWinningPlayer());
    }

    @Test
    public void gameOverCurrentPlayer() throws Exception {
	assertEquals(Chip.EMPTY, game.getCurrentPlayer());
    }
}
