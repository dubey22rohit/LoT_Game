package com.skorpion.lotgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
enum Player{
    ONE , TWO , NO;
}

Player currentPlayer = Player.ONE;
Player[] playerChoice = new Player[9];
int[][] winRC = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
boolean gameOver = false;
    private Button btnReset;
    private GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0;i<playerChoice.length;i++){
            playerChoice[i] = Player.NO;
        }
        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridView);

    }
    public void imgTap(View imageView){
        View imgTapped = (ImageView) imageView;
        int tiTag = Integer.parseInt(imgTapped.getTag().toString());


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            resetGame();
            }
        });
        if(playerChoice[tiTag] == Player.NO && gameOver == false) {
            imgTapped.setTranslationX(-2000);

            playerChoice[tiTag] = currentPlayer;
            if (currentPlayer == Player.ONE) {
                ((ImageView) imgTapped).setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                ((ImageView) imgTapped).setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }

            imgTapped.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);

            for (int[] winnerCols : winRC) {
                if (playerChoice[winnerCols[0]] == playerChoice[winnerCols[1]] && playerChoice[winnerCols[1]] == playerChoice[winnerCols[2]] && playerChoice[winnerCols[0]] != Player.NO) {
                    btnReset.setVisibility(View.VISIBLE);
                    gameOver = true;
                    String winner = "";
                    if(currentPlayer == Player.ONE){
                        winner = "Player Two";
                    }
                    else if(currentPlayer == Player.TWO){
                        winner = "Player One";
                    }
                    Toast.makeText(this,winner + " Winner Weiner Chimken Dinner!",Toast.LENGTH_LONG).show();


                }
            }
        }
    }
    private void resetGame(){
    for(int i = 0;i<gridLayout.getChildCount();i++){
        ImageView imageView = (ImageView)gridLayout.getChildAt(i);
        imageView.setImageDrawable(null);
        imageView.setAlpha(0.2f);
    }
        currentPlayer = Player.ONE;
            for(int i = 0;i<playerChoice.length;i++){
            playerChoice[i] = Player.NO;
        }
        gameOver = false;
        btnReset.setVisibility(View.INVISIBLE);
    }
}
