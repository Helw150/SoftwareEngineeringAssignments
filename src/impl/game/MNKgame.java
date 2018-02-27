package impl.game;

import java.util.Arrays;

import api.Chip;
import api.Game;

import exc.GameIndexOutOfBoundsException;
import exc.GameStateException;

public class MNKgame extends Game {
    private Chip[][] board;
    private Chip[] players;
    private Chip winningPlayer;
    private int numRows, numCols, numPlayers, currentPlayer, connectToWin;

    /* 
       ANT Build file calls it M,N,K Game so build functionality that allows for variable M,N in despite specifications of the lab.
       K is left out because it requires the use of counters which is destroys the beauty of my Boolean checks.
    */
    private MNKgame(){
    }

    // Take numRows and numColumns
    public MNKgame(int numRows, int numCols, int connectToWin){
	this.numRows = numRows;
	this.numCols = numCols;
	this.connectToWin = connectToWin;
	this.numPlayers = 2;
	// Set the initial user
	this.currentPlayer = 0;
	this.winningPlayer = Chip.EMPTY;
	this.board = new Chip[this.numRows][this.numCols];
	this.players = new Chip[this.numPlayers];
	// Set player chip colors
	this.players[0] = Chip.RED;
	this.players[1] = Chip.BLUE;
	// Setup the board as empty
	for(int i = 0; i < numRows; i++){
	    for(int j = 0; j < numCols; j++){
		board[i][j] = Chip.EMPTY;
	    }
	}
    }

    // Simple getter for rows
    public int getRows(){
	return this.numRows;
    }

    // Simple getter for columns
    public int getColumns(){
	return this.numCols;
    }

    // Getter for num to win
    public int getConnectToWin(){
	return this.connectToWin;
    }
    
    // Check if the chip is valid and then return its val if so
    public Chip getChip(int row, int col){
	if (row < 0 || col < 0 || this.numRows <= row || this.numCols <= col){
	    throw new GameIndexOutOfBoundsException(row, col);
	} else {
	    return this.board[row][col];
	}
    }

    // Check if the indices are valid and then call my internal place function that deals with just column
    public void placeChip(int row, int col) throws GameStateException {
	if(row != 0 || this.numCols <= col || col < 0 || (this.board[0][col] != Chip.EMPTY && !this.isGameOver())){
	    throw new GameIndexOutOfBoundsException(0, col);
	} else {
	    placeChip(col);
	}
	// Let observers know that everything has changed
	notifyObservers();
    }


    // Check if the game is over, otherwise simulate placed piece
    public void placeChip(int col) throws GameStateException {
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


    // A getter that simply confirms the game is over first and excepts if it isnt over
    public Chip getWinningPlayer() throws GameStateException{
	if (this.isGameOver() && this.winningPlayer != Chip.EMPTY){
	    return this.winningPlayer;
	} else {
	    throw new GameStateException();
	}
    }

    // If the game is over returns empty. Otherwise, simply a getter.
    public Chip getCurrentPlayer(){
	if(this.isGameOver()){
	    return Chip.EMPTY;
	} else {
	    return this.players[this.currentPlayer];
	}
    }

    // Check the four spaces to the right for a win if it is valid
    public boolean horizontalWin(int i, int j, Chip player){
	if(j < numCols-(connectToWin-1)){
	    boolean winner = true;
	    for(int k = 0; k < connectToWin; k++){
		winner = winner && (this.board[i][j+k] == player);
	    }
	    return winner;
	} else {
	    return false;
	}
    }
    
    // Check the fours spaces below for a win if it is valid
    public boolean verticalWin(int i, int j, Chip player){
	if(i < numRows-(connectToWin-1)){
	    boolean winner = true;
	    for(int k = 0; k < connectToWin; k++){
		winner = winner && (this.board[i+k][j] == player);
	    }
	    return winner;
	} else {
	    return false;
	}
    }

    // Check from top left to bottom right for a diagonal win
    public boolean top2bottomDiagonalWin(int i, int j, Chip player){
	if((connectToWin-1) <= i && (connectToWin-1) <= j){
	    boolean winner = true;
	    for(int k = 0; k < connectToWin; k++){
		winner = winner && (this.board[i-k][j-k] == player);
	    }
	    return winner;
	} else {
	    return false;
	}
    }

    // Check from bottom left to top right for a diagonal win
    public boolean bottom2topDiagonalWin(int i, int j, Chip player){
	if((connectToWin-1) <= i && j < numCols-(connectToWin-1)){
	    boolean winner = true;
	    for(int k = 0; k < connectToWin; k++){
		winner = winner && (this.board[i-k][j+k] == player);
	    }
	    return winner;
	} else {
	    return false;
	}
    }

    // Check all my win conditions
    public boolean isGameOver(){
	// Check every win condition in a single loop
	for (int i = 0; i < this.numRows; i++){
	    for(int j = 0; j < this.numCols; j++){
		// Check for wins on each player
		for(Chip player : this.players){
		    // Check all booleans - the validity of the space as a check spot is contained internally
		    if(horizontalWin(i,j,player) || verticalWin(i,j,player) || top2bottomDiagonalWin(i,j,player) || bottom2topDiagonalWin(i,j,player)){
			// if there is a win set my winning player to which of the two was found
			this.winningPlayer = player;
			return true;
		    }
		}
	    }
	}
	// Check that there are empty spaces in the board
	for (int i = 0; i < this.numCols; i++){
	    // We only need to check the top row due to falling
	    if(board[0][i] == Chip.EMPTY){
		return false;
	    }
	}
	// If the board was full and reached this point, the game is a tie.
	this.winningPlayer = Chip.EMPTY;
	return true;
    }
}
