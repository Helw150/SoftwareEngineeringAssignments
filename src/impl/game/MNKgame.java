package impl.game;

import java.util.Arrays;

import api.Chip;
import api.Game;

import exc.GameIndexOutOfBoundsException;
import exc.GameStateException;

public abstract class MNKgame extends Game {
    private Chip winningPlayer;
    protected Chip[][] board;
    protected Chip[] players;
    protected int numRows, numCols, numPlayers, currentPlayer, connectToWin;

    /* 
       ANT Build file calls it M,N,K Game so build functionality that allows for variable M,N in despite specifications of the lab.
       K is left out because it requires the use of counters which is destroys the beauty of my Boolean checks.
    */
    private MNKgame(){}
    
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

    // The function which has to be changed for all MNK games
    public abstract void place(int row, int col) throws GameStateException;

    // Function to handle checking for a and handling a full board;
    public abstract boolean nonWinEnd();
    
    
    // The Template for which all other MNKgames must follow
    public void placeChip(int row, int col) throws GameStateException {
	if(this.isGameOver()){
	    throw new GameStateException();
	} else if(this.numCols <= col || this.numRows <= row || row < 0 || col < 0){
	    throw new GameIndexOutOfBoundsException(row, col);
	} else {
	    this.place(row, col);
	    // If the game isn't over iterate to the next player
	    if(!this.isGameOver()){
		this.currentPlayer = (this.currentPlayer+1)%this.numPlayers;
	    }
	    // Inform the observable that it has changed
	    setChanged();
	}
	notifyObservers();
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
	int[] wins = new int[this.numPlayers];
	// Check every win condition in a single loop
	for (int i = 0; i < this.numRows; i++){
	    for(int j = 0; j < this.numCols; j++){
		// Check for wins on each player
		for(int player = 0; i < 2; i++){
		    // Check all booleans - the validity of the space as a check spot is contained internally
		    if(horizontalWin(i,j,this.players[player]) || verticalWin(i,j,this.players[player]) || top2bottomDiagonalWin(i,j,this.players[player]) || bottom2topDiagonalWin(i,j,this.players[player])){
			
			// if there is a win set my winning player to which of the two was found
			wins[player]++;
		    }
		}
	    }
	}
	// If the wins aren't equal there is a winner
	if(wins[0] != wins[1]){
	    // If 0 has more wins than 1 they are the winner else 1 is the winner since we know they are not equal
	    this.winningPlayer = (wins[0] > wins[1]) ? this.players[0] : this.players[1];
	    return true;
	} else {
	    return nonWinEnd();
	}
    }
}
