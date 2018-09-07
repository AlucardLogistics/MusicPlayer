package com.android.alucard.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Button btPlay, btStop, btNext, btPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: play music");
                Intent i = new Intent(MainActivity.this, MusicService.class);
                i.putExtra("command", "play");
                startService(i);
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MusicService.class);
                stopService(i);
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MusicService.class);
                i.putExtra("command", "next");
                startService(i);
            }
        });

        btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MusicService.class);
                i.putExtra("command", "prev");
                startService(i);
            }
        });


    }

    private void initView() {
        btPlay = findViewById(R.id.btPlay);
        btStop = findViewById(R.id.btStop);
        btNext = findViewById(R.id.btNext);
        btPrev = findViewById(R.id.btPrev);
    }
}
