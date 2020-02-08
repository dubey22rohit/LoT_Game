package com.skorpion.lotgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
enum Player{
    ONE , TWO , NO;
}

Player currentPlayer = Player.ONE;
Player[] playerChoice = new Player[9];
int[][] winRC = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerChoice[0] = Player.NO;
        playerChoice[1] = Player.NO;
        playerChoice[2] = Player.NO;
        playerChoice[3] = Player.NO;
        playerChoice[4] = Player.NO;
        playerChoice[5] = Player.NO;
        playerChoice[6] = Player.NO;
        playerChoice[7] = Player.NO;
        playerChoice[8] = Player.NO;
    }
    public void imgTap(View imageView){
        View imgTapped = (ImageView) imageView;
        int tiTag = Integer.parseInt(imgTapped.getTag().toString());
        if(playerChoice[tiTag] == Player.NO) {
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
}
