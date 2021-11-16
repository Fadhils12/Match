package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MatchActivity extends AppCompatActivity {

    private int seconds = 0;

    private boolean running;

    private boolean wasRunning;

    private int counterHome = 0;

    private int counterAway = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        //for Stopwatch
        if (savedInstanceState != null) {

            seconds
                    = savedInstanceState
                    .getInt("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");

        }
        runTimer();


        //for data edit text
        final TextView textHome = findViewById(R.id.txt_home);
        final TextView textAway = findViewById(R.id.txt_away);

        ImageView btnScoreHome = findViewById(R.id.btn_score_home);
        ImageView btnScoreAway = findViewById(R.id.btn_score_away);

        final Button btnResult = findViewById(R.id.btn_check);

        final TextView scoreHome = findViewById(R.id.score_home);
        final TextView scoreAway = findViewById(R.id.score_away);

        textHome.setText(getIntent().getStringExtra("home"));
        textAway.setText(getIntent().getStringExtra("away"));

        btnScoreHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                counterHome++;

                scoreHome.setText(Integer.toString(counterHome));
            }
        });

        btnScoreAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counterAway++;
                scoreAway.setText(Integer.toString(counterAway));
            }
        });
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MatchActivity.this, ResultActivity.class);

                if (counterHome > counterAway){
                    intent.putExtra("team", textHome.getText().toString());
                } else if (counterAway > counterHome){
                    intent.putExtra("team", textAway.getText().toString());
                }

                startActivity(intent);

            }
        });
    }

    private void runTimer() {

        final TextView timeView
                = (TextView) findViewById(
                R.id.tv_stopwatch);

        final Handler handler
                = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time
                        = String
                        .format(Locale.getDefault(),
                                "%d:%02d:%02d", hours,
                                minutes, secs);

                timeView.setText(time);

                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickRestart(View view) {
        running = false;
        seconds = 0;
    }

    public void onClickPlay(View view) {
        running = true;
    }
}