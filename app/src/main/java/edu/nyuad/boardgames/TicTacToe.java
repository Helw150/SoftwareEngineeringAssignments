package edu.nyuad.boardgames;

import java.util.Arrays;

import edu.nyuad.boardgames.Chip;
import edu.nyuad.boardgames.Game;

import edu.nyuad.boardgames.MNKgame;
    
import edu.nyuad.boardgames.GameIndexOutOfBoundsException;
import edu.nyuad.boardgames.GameStateException;

public class TicTacToe extends MNKgame {

    public TicTacToe(){
	super(3,3,3);
    }

        // Check if the indices are valid and then call my internal place function that deals with just column
    public void place(int row, int col) throws GameStateException {
	if(this.board[row][col] != Chip.EMPTY){
	    throw new GameIndexOutOfBoundsException(0, col);
	} else {
	    this.board[row][col] = this.players[this.currentPlayer];
	}
    }

    public boolean nonWinEnd(){
	// Check that there are empty spaces in the board
	for (int i = 0; i < this.numCols; i++){
	    for(int j = 0; j < this.numRows; j++){
		// We only need to check the top row due to falling
		if(board[j][i] == Chip.EMPTY){
		    return false;
		}
	    }
	}
	return true;
    }
}
