package edu.nyuad.androidgames;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Button;
import android.content.Intent;

import java.util.Observer;
import java.util.Observable;

import edu.nyuad.boardgames.Complica;
import edu.nyuad.boardgames.ConnectFour;
import edu.nyuad.boardgames.Game;
import edu.nyuad.boardgames.TicTacToe;

public class Console extends AppCompatActivity implements Observer{

    int rows, cols;
    private Game game;
    GridLayout grid;
    Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.console);
        Intent intent = getIntent();
        rows = intent.getIntExtra("rows", 0);
        cols = intent.getIntExtra("cols", 0);
        grid = (GridLayout) findViewById(R.id.grid);
        grid.setColumnCount(cols);
        grid.setRowCount(rows);
        buttons = new Button[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                buttons[i][j] = new Button(this);
                grid.addView(buttons[i][j]);
            }
        }
        // I would rather pass the game as an extra
        // However, to do so would require a modification of the Game API
        // Making it implement either parcelable or serializable
        // which would violate the spec given to me
        switch(intent.getStringExtra("type")){
            case "complica":
                this.game = new Complica();
                break;
            case "connectFour":
                this.game = new ConnectFour();
                break;
            case "ticTacToe":
                this.game = new TicTacToe();
                break;
            default:
                this.game = new ConnectFour();
                break;
        }
        this.game.addObserver(this);
    }

    public void render(Game game){

    }

    public void update(Observable o, Object game) {
        render(this.game);
    }
    public void goBack(View view){
        Intent i = new Intent(Console.this, MainActivity.class);
        Console.this.startActivity(i);
    }

}
