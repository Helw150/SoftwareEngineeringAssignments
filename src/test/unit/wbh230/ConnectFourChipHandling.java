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

public class ConnectFourChipHandling {
    private Game game;
    
    @Before
    public void setup() {
	game = new ConnectFour();
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
    public void placeNonZeroRow() throws Exception {
        game.placeChip(1, 1);
    }

    @Test(expected = GameIndexOutOfBoundsException.class)
    public void placeFilledRow() throws Exception {
        for(int i = 0; i < game.getRows()+1; i++){
	    game.placeChip(0, 0);
	}
    }

    @Test(expected = GameStateException.class)
    public void placeAfterWin() throws Exception {
        for(int i = 0; i < game.getRows()+1; i++){
	    game.placeChip(0, 0);
	    game.placeChip(0, 1);
	}
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
    public void placeAnySpot() throws Exception {
	int count = 0;
	for (int i = 0; i < game.getRows(); i++) {
	    game = new ConnectFour();
	    for (int j = 0; j < game.getColumns(); j++) {
		game.placeChip(0, j);
		count++;
	    }
	}
	assertEquals(game.getRows()*game.getColumns(), count);
    }
}
