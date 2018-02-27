package impl.game;

import java.util.Arrays;

import api.Chip;
import api.Game;

import impl.game.MNKgame;
    
import exc.GameIndexOutOfBoundsException;
import exc.GameStateException;

public class ConnectFour extends MNKgame {

    public ConnectFour(){
	super(6,7,4);
    }

}
