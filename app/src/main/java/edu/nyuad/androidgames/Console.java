package edu.nyuad.androidgames;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import java.util.Observer;
import java.util.Observable;

import edu.nyuad.boardgames.Chip;
import edu.nyuad.boardgames.Complica;
import edu.nyuad.boardgames.ConnectFour;
import edu.nyuad.boardgames.Game;
import edu.nyuad.boardgames.GameStateException;
import edu.nyuad.boardgames.GameIndexOutOfBoundsException;
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
        // Get some screen dimension info to size my buttons
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        int width = ((screenWidth)/cols);
        int height = ((screenHeight-400)/rows);
        buttons = new Button[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                buttons[i][j] = new Button(this);
                placeOnClick(buttons[i][j], i, j);
                GridLayout.LayoutParams btn = new GridLayout.LayoutParams();
                btn.width = width;
                btn.height = height;
                buttons[i][j].setLayoutParams(btn);
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
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                Chip val = game.getChip(i,j);
                if (val == Chip.BLUE){
                    buttons[i][j].setBackgroundColor(Color.parseColor("#ff0000"));
                } else if (val == Chip.RED){
                    buttons[i][j].setBackgroundColor(Color.parseColor("#0000ff"));
                }
            }
        }
    }

    public void placeClick(int i, int j){
        try {
            this.game.placeChip(i,j);
        }
        // This should only occur if the game is appropriately over, so break and move to the higher level announcement
        catch (GameStateException e) {
            try {
                Chip winner = this.game.getWinningPlayer();
                Toast.makeText(this, winner + " wins!", Toast.LENGTH_LONG).show();
            }
            catch (GameStateException l) {
                Toast.makeText(this, "It was a tie!", Toast.LENGTH_LONG).show();
            }
            Toast.makeText(this, "Invalid Range for Move", Toast.LENGTH_LONG).show();
        } catch (GameIndexOutOfBoundsException e){
            Toast.makeText(this, "Invalid Range for Move", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e){
            Toast.makeText(this, "Please input a number", Toast.LENGTH_LONG).show();
        }
    }
    public void update(Observable o, Object game) {
        render(this.game);
        if(this.game.isGameOver()){
            placeClick(0, 0);
        }
    }
    public void goBack(View view){
        Intent i = new Intent(Console.this, MainActivity.class);
        Console.this.startActivity(i);
    }

    private void placeOnClick(final Button btn, final int i, final int j) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeClick(i, j);
            }
        });
    }
}
