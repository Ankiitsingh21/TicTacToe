package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {
    private Button reset;
    boolean gameactive = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(!gameactive)
        {
            gamereset(view);
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.zero);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("X : turn to play");
            }
            else {
                img.setImageResource(R.drawable.x);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText(" 0 : turn to play");
            }

            img.animate().translationY(0f).setDuration(300);
           for(int [] winPosition : winPositions )
           {
               if(gameState[winPosition[0]]==gameState[winPosition[1]]&&
                  gameState[winPosition[1]]==gameState[winPosition[2]]&&
                   gameState[winPosition[0]]!=2)
               {
                   String winner;
                   gameactive = false;
                   if(gameState[winPosition[0]]==0)
                   {
                       winner="Zero Wons";
                   }
                   else
                   {
                       winner="X Wons";
                   }
                   TextView status = findViewById(R.id.status);
                   status.setText(winner);
               }
           }
           reset = (Button) findViewById(R.id.reset);
           reset.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   gamereset(view);
               }
           });
           boolean isDraw = true;
           for(int state : gameState)
           {
               if(state == 2)
               {
                   isDraw = false;
                   break;
               }
           }
           if(isDraw)
           {
               gamereset(view);
           }

        }
    }


    public void gamereset(View view)
    {
          gameactive = true;
          activePlayer = 0;
          for(int i=0;i<gameState.length;i++)
          {
              gameState[i] = 2;
          }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById((R.id.status));
        status.setText("Status");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomescreen);
    }
}
