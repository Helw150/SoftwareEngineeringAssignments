package edu.nyuad.androidgames;

import edu.nyuad.boardgames.Game;
import edu.nyuad.boardgames.Complica;
import edu.nyuad.boardgames.TicTacToe;
import edu.nyuad.boardgames.ConnectFour;

import edu.nyuad.androidgames.Console;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void changeActivity(View view, Game game, String gameType) {
        Intent myIntent = new Intent(MainActivity.this, Console.class);
        myIntent.putExtra("rows", game.getRows());
        myIntent.putExtra("cols", game.getColumns());
        myIntent.putExtra("type", gameType);
        MainActivity.this.startActivity(myIntent);
    }

    public void complicaConsole(View view) {
        Game complica = new Complica();
        this.changeActivity(view, complica, "complica");
    }

    public void connectFourConsole(View view) {
        Game connectFour = new ConnectFour();
        this.changeActivity(view, connectFour, "connectFour");
    }

    public void ticTacToeConsole(View view) {
        Game ticTacToe = new TicTacToe();
        this.changeActivity(view, ticTacToe, "ticTacToe");
    }

}
