package impl.game;

import java.util.Arrays;

import api.Chip;
import api.Game;

import exc.GameIndexOutOfBoundsException;
import exc.GameStateException;

public class ConnectFour extends Game {
    private Chip[][] board;
    private Chip[] players;
    private Chip winningPlayer;
    private int numRows, numCols, numPlayers, currentPlayer, connectToWin;

    /* 
       ANT Build file calls it M,N,K Game so build functionality that allows for variable M,N in despite specifications of the lab.
       K is left out because it requires the use of counters which is destroys the beauty of my Boolean checks.
    */
    public ConnectFour(){
	this(6, 7);
    }
    
    public ConnectFour(int numRows, int numCols){
	this.numRows = numRows;
	this.numCols = numCols;
	this.connectToWin = connectToWin;
	this.numPlayers = 2;
	this.currentPlayer = 0;
	this.winningPlayer = Chip.EMPTY;
	this.board = new Chip[this.numRows][this.numCols];
	this.players = new Chip[this.numPlayers];
	this.players[0] = Chip.RED;
	this.players[1] = Chip.BLUE;
	for(int i = 0; i < numRows; i++){
	    Arrays.fill(board[i], Chip.EMPTY);
	}
    }

    public int getRows(){
	return this.numRows;
    }

    public int getColumns(){
	return this.numCols;
    }

    public Chip getChip(int row, int col){
	if (this.numRows <= row || this.numCols <= col){
	    throw new GameIndexOutOfBoundsException(row, col);
	} else {
	    return this.board[row][col];
	}
    }

    public void placeChip(int col) throws GameStateException {
	if(board[0][col] != Chip.EMPTY){
	    throw new GameIndexOutOfBoundsException(0, col);
	}
	for(int i = 0; i < numRows; i++){
	    if(board[i][col] == Chip.EMPTY){
		placeChip(i, col);
	    }
	}
    }
    
    public void placeChip(int row, int col) throws GameStateException {
	if (this.numRows <= row || this.numCols <= col){
	    throw new GameIndexOutOfBoundsException(row, col);
	} else if(this.isGameOver()) {
	    throw new GameStateException();
	} else {
	    this.board[row][col] = this.players[this.currentPlayer];
	    if(this.isGameOver()){
		this.winningPlayer = this.players[this.currentPlayer];
	    } else {
		this.currentPlayer = (this.currentPlayer+1)%this.numPlayers;
	    }
	}
    }

    public Chip getWinningPlayer() throws GameStateException{
	if (this.isGameOver()){
	    return this.winningPlayer;
	} else {
	    throw new GameStateException();
	}
    }

    public Chip getCurrentPlayer(){
	if(this.isGameOver()){
	    return Chip.EMPTY;
	} else {
	    return this.players[this.currentPlayer];
	}
    }
    
    public boolean horizontalWin(int i, int j, Chip player){
	if(j < numCols-(connectToWin-1)){
	    return (this.board[i][j] == player && this.board[i][j+1] == player && this.board[i][j+2] == player && this.board[i][j+3] == player);
	} else {
	    return false;
	}
    }

    public boolean verticalWin(int i, int j, Chip player){
	if(i < numRows-(connectToWin-1)){
	    return (this.board[i][j] == player && this.board[i+1][j] == player && this.board[i+2][j] == player && this.board[i+3][j] == player);
	} else {
	    return false;
	}
    }

    public boolean top2bottomDiagonalWin(int i, int j, Chip player){
	if(3 < i && 3 < j){
	    return (this.board[i][j] == player && this.board[i-1][j-1] == player && this.board[i-2][j-2] == player && this.board[i-3][j-3] == player);
	} else {
	    return false;
	}
    }

    public boolean bottom2topDiagonalWin(int i, int j, Chip player){
	if(3 < i && j < numCols-(connectToWin-1)){
	    return (this.board[i][j] == player && this.board[i-1][j-1] == player && this.board[i-2][j-2] == player && this.board[i-3][j-3] == player);
	} else {
	    return false;
	}
    }

    public boolean isGameOver(){
	for (int i = 0; i < this.numRows; i++){
	    for(int j = 0; j < this.numCols; j++){
		for(Chip player : this.players){
		    if(horizontalWin(i,j,player) || verticalWin(i,j,player) || top2bottomDiagonalWin(i,j,player) || bottom2topDiagonalWin(i,j,player)){
			return true;
		    }
		}
	    }
	}
	for (int i = 0; i < this.numRows; i++){
	    if(board[0][i] == Chip.EMPTY){
		return false;
	    }
	}
	return true;
    }
}
