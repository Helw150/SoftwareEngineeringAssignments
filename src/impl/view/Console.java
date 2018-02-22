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
    
    public Console(Game game){
	this.game = game;
	this.game.addObserver(this);
    }
    
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

    public void play(Game game){
	System.out.print("Select a Column to Play: ");
	Scanner stdin = new Scanner(System.in);
	try {
	    int col = Integer.parseInt(stdin.next());
	    game.placeChip(0, col);
	}
	catch (GameStateException e) {
	    System.out.println("Invalid play given the state of the game.");
	} catch (GameIndexOutOfBoundsException e){
	    System.out.println("Invalid Range for move.");
	    play(this.game);
	} catch (NumberFormatException e){
	    System.out.println("Please input a number.");
	    play(this.game);
	}
      
    }

    public void update(Observable o, Object game) {
        render(this.game);
	play(this.game);
    }
}
