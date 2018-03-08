package edu.nyuad.androidgames;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import edu.nyuad.boardgames.Game;

public class Console extends AppCompatActivity {

    int rows, cols;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.console);
        Intent intent = getIntent();
        rows = intent.getIntExtra("rows");
        cols = intent.getIntExtra("cols");
        switch(intent.getStringExtra("type")){
            case "complica":
                break;
            case "connectFour":
                break;
            case "ticTacToe":
                break;
            default:
                break;
        }
    }

    public void goBack(View view){
        Intent i = new Intent(Console.this, MainActivity.class);
        Console.this.startActivity(i);
    }

}
