package edu.nyuad.boardgames;

import java.util.Arrays;

import edu.nyuad.boardgames.Chip;
import edu.nyuad.boardgames.Game;

import edu.nyuad.boardgames.MNKgame;
    
import edu.nyuad.boardgames.GameIndexOutOfBoundsException;
import edu.nyuad.boardgames.GameStateException;

public class Complica extends MNKgame {

    public Complica(){
	super(7,4,4);
    }

        // Check if the indices are valid and then call my internal place function that deals with just column
    public void place(int row, int col) throws GameStateException {
	if(row != 0){
	    throw new GameIndexOutOfBoundsException(0, col);
	} else {
	    place(col);
	}
    }

    private void dropPiece(int col){
	for(int i = numRows-1; i > 0; --i){
	    this.board[i][col] = this.board[i-1][col];
	}
	this.board[0][col] = Chip.EMPTY;
    }
    
    // Check if the game is over, otherwise simulate placed piece
    public void place(int col) throws GameStateException {
	if(this.isGameOver()) {
	    throw new GameStateException();
	} else {
	    int row;
	    if(this.board[0][col] == Chip.EMPTY){
		// Simulates the "falling" of the piece
		row = 0;
		for(int i = 0; i < numRows; i++){
		    if(board[i][col] == Chip.EMPTY){
			row = i;
		    }
		}
	    } else {
		// Simulates pushing the pieces out
		dropPiece(col);
		row = 0;
	    }
	    // Set the requested piece to the current players color
	    this.board[row][col] = this.players[this.currentPlayer];
	}
    }

    public boolean nonWinEnd(){
	return false;
    }
}
