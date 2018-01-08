package com.example.android.footballplayer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MatchSimulation extends AppCompatActivity {

    boolean isActive = false;
    int scorePoland = 0;
    int scoreSlovakia = 0;
    int cornerPoland = 0;
    int cornerSlovakia = 0;
    int redCardPoland = 0;
    int yellowCardPoland = 0;
    int redCardSlovakia = 0;
    int yellowCardSlovakia = 0;

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_simulation);
    }

    /**
     * Start game
     */
    public void startGame(View view) {
        if (!isActive) {
            isActive = true;
            timer = new CountDownTimer(60000, 1000) {
                TextView counter = (TextView) findViewById(R.id.counter);

                public void onTick(long millisUntilFinished) {
                    counter.setText(String.valueOf(millisUntilFinished / 1000));
                }

                public void onFinish() {
                    counter.setText("Game's over!");
                    isActive = false;
                    TextView ballPossession = (TextView) findViewById(R.id.ball_possession);
                    Random generator = new Random();
                    int polandPossession = generator.nextInt(10) - 1;
                    int slovakiaPossession = 10 - polandPossession;
                    ballPossession.setText("Poland: " + polandPossession * 10 + " | Slovakia: " + slovakiaPossession * 10);
                }
            }.start();
        }
    }

    public void addGoalForPoland(View view) {
        TextView scoreCount = (TextView) findViewById(R.id.poland_score);
        if (isActive) {
            scorePoland++;
            scoreCount.setText(String.valueOf(scorePoland));
        }
    }

    public void addCornerForPoland(View view) {
        TextView cornerCount = (TextView) findViewById(R.id.poland_corner);
        if (isActive) {
            cornerPoland++;
            cornerCount.setText(String.valueOf(cornerPoland));
            if ((cornerPoland % 5) == 0) {
                scorePoland++;
                TextView scoreCount = (TextView) findViewById(R.id.poland_score);
                scoreCount.setText(String.valueOf(scorePoland));
            }
        }
    }

    public void addCardForPoland(View view) {
        TextView cardCount = (TextView) findViewById(R.id.poland_card);
        if (isActive) {
            yellowCardPoland++;
            if ((yellowCardPoland % 4) == 0) {
                redCardPoland++;
                if (redCardPoland >= 4) {
                    timer.cancel();
                    timer.onFinish();
                    scorePoland = 0;
                    scoreSlovakia = 0;
                    cornerPoland = 0;
                    cornerSlovakia = 0;
                    redCardPoland = 0;
                    yellowCardPoland = 0;
                    redCardSlovakia = 0;
                    yellowCardSlovakia = 0;
                    TextView counter = (TextView) findViewById(R.id.counter);
                    counter.setText("Walkover!");
                    TextView winnerCount = (TextView) findViewById(R.id.slovakia_score);
                    winnerCount.setText("3");
                    TextView loserCount = (TextView) findViewById(R.id.poland_score);
                    loserCount.setText("0");
                }
            }
            cardCount.setText("Y: " + String.valueOf(yellowCardPoland) + " | R: " + String.valueOf(redCardPoland));
        }
    }

    public void addGoalForSlovakia(View view) {
        TextView scoreCount = (TextView) findViewById(R.id.slovakia_score);
        if (isActive) {
            scoreSlovakia++;
            scoreCount.setText(String.valueOf(scoreSlovakia));
        }
    }

    public void addCornerForSlovakia(View view) {
        TextView cornerCount = (TextView) findViewById(R.id.slovakia_corner);
        if (isActive) {
            cornerSlovakia++;
            cornerCount.setText(String.valueOf(cornerSlovakia));
            if ((cornerSlovakia % 5) == 0) {
                scoreSlovakia++;
                TextView scoreCount = (TextView) findViewById(R.id.slovakia_score);
                scoreCount.setText(String.valueOf(scoreSlovakia));
            }
        }
    }

    public void addCardForSlovakia(View view) {
        TextView cardCount = (TextView) findViewById(R.id.slovakia_card);
        if (isActive) {
            yellowCardSlovakia++;
            if ((yellowCardSlovakia % 4) == 0) {
                redCardSlovakia++;
                if (redCardSlovakia >= 4) {
                    timer.cancel();
                    isActive = false;
                    scorePoland = 0;
                    scoreSlovakia = 0;
                    cornerPoland = 0;
                    cornerSlovakia = 0;
                    redCardPoland = 0;
                    yellowCardPoland = 0;
                    redCardSlovakia = 0;
                    yellowCardSlovakia = 0;
                    TextView counter = (TextView) findViewById(R.id.counter);
                    counter.setText("Walkover!");
                    TextView winnerCount = (TextView) findViewById(R.id.poland_score);
                    winnerCount.setText("3");
                    TextView loserCount = (TextView) findViewById(R.id.slovakia_score);
                    loserCount.setText("0");
                }
            }
            cardCount.setText("Y: " + String.valueOf(yellowCardSlovakia) + " | R: " + String.valueOf(redCardSlovakia));
        }
    }

    public void reset(View view) {
        setContentView(R.layout.activity_match_simulation);
        if (timer != null) {
            timer.cancel();
        }
        isActive = false;
        scorePoland = 0;
        scoreSlovakia = 0;
        cornerPoland = 0;
        cornerSlovakia = 0;
        redCardPoland = 0;
        yellowCardPoland = 0;
        redCardSlovakia = 0;
        yellowCardSlovakia = 0;
    }
}