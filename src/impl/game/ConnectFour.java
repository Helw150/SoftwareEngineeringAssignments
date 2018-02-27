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

        // Check if the indices are valid and then call my internal place function that deals with just column
    public void place(int row, int col) throws GameStateException {
	if(row != 0 || (this.board[0][col] != Chip.EMPTY && !this.isGameOver())){
	    throw new GameIndexOutOfBoundsException(0, col);
	} else {
	    place(col);
	}
	// Let observers know that everything has changed
	notifyObservers();
    }


    // Check if the game is over, otherwise simulate placed piece
    public void place(int col) throws GameStateException {
	if(this.isGameOver()) {
	    throw new GameStateException();
	} else {
	    // Simulates the "falling" of the piece
	    int row = 0;
	    for(int i = 0; i < numRows; i++){
		if(board[i][col] == Chip.EMPTY){
		    row = i;
		}
	    }
	    // Set the requested piece to the current players color
	    this.board[row][col] = this.players[this.currentPlayer];
	    // If the game isn't over iterate to the next player
	    if(!this.isGameOver()){
		this.currentPlayer = (this.currentPlayer+1)%this.numPlayers;
	    }
	    // Inform the observable that it has changed
	    setChanged();
	}
    }

}
