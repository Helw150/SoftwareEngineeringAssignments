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

public class ConnectFourPlayState {
    private Game game;
    
    @Before
    public void setup() {
	game = new ConnectFour();
    }

    @Test
    public void getChangePlayer() throws Exception {
    	Chip initial_player = game.getCurrentPlayer();
    	game.placeChip(0,0);
    	assertFalse(game.getCurrentPlayer() == initial_player);
    }

    @Test
    public void getPlaceRegisters() throws Exception {
    	Chip initial_player = game.getCurrentPlayer();
    	game.placeChip(0,0);
    	assertEquals(initial_player, game.getChip(game.getRows()-1, 0));
    }
    
    @Test
    public void vertGameOver() throws Exception {
    	for(int i = 0; i < 4; i++){
    	    game.placeChip(0,0);
    	    if (i < 3)
    		game.placeChip(0,1);
    	}
    	assertTrue("Game Finished", game.isGameOver());
    }

    @Test
    public void horzGameOver() throws Exception {
    	for(int i = 0; i < 4; i++){
    	    game.placeChip(0,i);
    	    if (i < 3)
    		game.placeChip(0,i);
    	}
    	assertTrue("Game Finished", game.isGameOver());
    }

    @Test
    public void top2botDiagGameOver() throws Exception {
    	int p1;
    	int p2;
    	for(int i = 0; i < 4; i++){
    	    for(int j = 0; j < 4-i; j++){
    		game.placeChip(0, i);
    	    }
    	    if(i%2==0)
    		game.placeChip(0,5);
    	}
    	assertTrue("Game Finished", game.isGameOver());
    }

    @Test
    public void bot2topDiagGameOver() throws Exception {
    	int p1;
    	int p2;
    	for(int i = 0; i < 4; i++){
    	    for(int j = 0; j < i+1; j++){
    		game.placeChip(0, i);
    	    }
    	    if(i==1)
    		game.placeChip(0,5);
    	}
    	assertTrue("Game Finished", game.isGameOver());
    }

    @Test(expected = GameStateException.class)
    public void tieGameOver() throws Exception {
	for(int i = 0; i < game.getRows(); i++){
	    for(int j = 0; j < (game.getColumns())/2; j++){
		int location = j*2;
		if(i<3){
		    game.placeChip(0,location);
		    game.placeChip(0,location+1);
		} else {
		    game.placeChip(0,location+1);
		    game.placeChip(0,location);
		}
	    }
	}
	for(int i = 0; i < game.getRows(); i++){
	    game.placeChip(0,6);
	}
	game.getWinningPlayer();
    }
}
