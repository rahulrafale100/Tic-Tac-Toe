package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int player=0;
    int active=1;
    String winner = "";
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int[][] win={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void drop(View view){
        if(active==1) {
            ImageView image = (ImageView) view;
            image.setTranslationY(-1500);
            int tapped = Integer.parseInt(image.getTag().toString());
            if (player == 0) {
                image.setImageResource(R.drawable.cross);
                gameState[tapped] = 0;
                player = 1;
            } else {
                image.setImageResource(R.drawable.circle);
                gameState[tapped] = 1;
                player = 0;
            }
            image.animate().translationYBy(1500).setDuration(300);
            for (int[] k : win) {
                if (gameState[k[0]] == gameState[k[1]] && gameState[k[1]] == gameState[k[2]] && gameState[k[0]] != 2) {
                    active=0;
                    if (player == 1) {
                        winner = "Cross has Won";
                    } else {
                        winner = "Circle has won";

                    }
                    break;
                }
            }
            if(active==1){
                int c=0;
                for(int i=0;i<9;i++){
                    if(gameState[i]==2)
                        c++;
                }
                if(c==0)
                {
                    active=0;
                    winner="Draw";
                }
            }
            if(active==0) {
                Button btn = (Button) findViewById(R.id.button);
                TextView txt=(TextView) findViewById(R.id.textView);
                txt.setText(winner);
                txt.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
            }

        }
    }
    public void again(View view){
        Button btn = (Button) findViewById(R.id.button);
        TextView txt=(TextView) findViewById(R.id.textView);
        txt.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.INVISIBLE);
        winner="";
        active=1;
        player=0;
        for(int i=0;i<9;i++){
            gameState[i]=2;
        }
        TableLayout table=(TableLayout) findViewById(R.id.tableLayout);
        for(int i=0;i<table.getChildCount();i++){
            TableRow row=(TableRow) table.getChildAt(i);
            for(int j=0;j<row.getChildCount();j++){
                ImageView image=(ImageView) row.getChildAt(j);
                image.setImageDrawable(null);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}