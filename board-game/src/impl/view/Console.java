package impl.view;

import java.util.Observable;
import java.util.Scanner;

import api.Chip;
import api.Game;
import api.View;

import exc.GameStateException;
import exc.GameIndexOutOfBoundsException;
    
public class Console extends View {
    private Game game;

    // Add this console using aggregate method to the game given to it as an Observer
    public Console(Game game){
	this.game = game;
	this.game.addObserver(this);
    }

    // Render the whole game using some basic dividers and the Enum name
    public void render(Game game){
	System.out.println("===========================");
	for(int i = 0; i < game.getRows(); i++){
	    for(int j = 0; j < game.getColumns(); j++){
		System.out.print("| " + game.getChip(i, j) + " |");
	    }
	    System.out.println("");
	    System.out.println("---------------------------------");
	}
    }

    // Take User Input from the Game
    public void play(Game game){
	try {
	    Scanner stdin = new Scanner(System.in);
	    if (game.getCurrentPlayer() != Chip.EMPTY) {
		System.out.println(game.getCurrentPlayer() +" player - select a Row and a Column to Play: ");
		int row = Integer.parseInt(stdin.next());
		int col = Integer.parseInt(stdin.next());
		game.placeChip(row, col);
	    } else {
		game.placeChip(0, 0);
	    }
	}
	// This should only occur if the game is appropriately over, so break and move to the higher level announcement
	catch (GameStateException e) {
	    return;
	} catch (GameIndexOutOfBoundsException e){
	    System.out.println("Invalid Range for move.");
	    // Give the user the choice to play again
	    play(game);
	} catch (NumberFormatException e){
	    System.out.println("Please input a number.");
	    // Give the user the choice to play again
	    play(game);
	}
      
    }

    // Render the updated board and give the user another option to play
    public void update(Observable o, Object game) {
        render(this.game);
	play(this.game);
    }
}
