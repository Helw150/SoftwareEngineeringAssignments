package edu.nyuad.boardgames;

import java.util.Arrays;

import edu.nyuad.boardgames.Chip;
import edu.nyuad.boardgames.Game;

import edu.nyuad.boardgames.MNKgame;
    
import edu.nyuad.boardgames.GameIndexOutOfBoundsException;
import edu.nyuad.boardgames.GameStateException;

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
	}
    }

    public boolean nonWinEnd(){
	// Check that there are empty spaces in the board
	for (int i = 0; i < this.numCols; i++){
	    // We only need to check the top row due to falling
	    if(board[0][i] == Chip.EMPTY){
		return false;
	    }
	}
	return true;
    }
}
