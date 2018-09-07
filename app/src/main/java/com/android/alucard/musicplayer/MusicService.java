package com.android.alucard.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service {
    private static final String TAG = "MusicService";

    public MusicService() {
        //required constructor
    }

    MediaPlayer mediaPlayer;
    int[] playList = {R.raw.warbringers_jaina, R.raw.linkin_park_numb, R.raw.metallica_fade_to_black};
    int trackNo = 0;
    int playlistLenght = playList.length;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //mediaPlayer = MediaPlayer.create(MusicService.this, playList[trackNo]);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: start music");

        String command = intent.getStringExtra("command");
        Log.d(TAG, "onStartCommand: playlistLenght: " + playlistLenght);
        //Log.d(TAG, "onStartCommand: command " + command);

        if(trackNo < playlistLenght && trackNo >= 0) {
            if(command.equals("next")){
                Log.d(TAG, "onNext: " + command);
                if(trackNo == playlistLenght - 1){
                    Log.d(TAG, "onStartCommand: if track is last");
                    trackNo = playlistLenght - 1;
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MusicService.this, playList[trackNo]);
                    mediaPlayer.start();
                    Toast.makeText(this, "Last Tune", Toast.LENGTH_SHORT).show();
                } else {
                    trackNo += 1;
                    Log.d(TAG, "onNext trackNo " + trackNo);
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MusicService.this, playList[trackNo]);
                    mediaPlayer.start();
                }
            } else if(command.equals("prev")){
                Log.d(TAG, "onPrev: " + command);
                if(trackNo == 0){
                    Log.d(TAG, "onStartCommand: if track is 0");
                    trackNo = 0;
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MusicService.this, playList[trackNo]);
                    mediaPlayer.start();
                    Toast.makeText(this, "First Tune", Toast.LENGTH_SHORT).show();
                } else {
                    trackNo -= 1;
                    Log.d(TAG, "onPrev trackNo " + trackNo);
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MusicService.this, playList[trackNo]);
                    mediaPlayer.start();
                }
            } else if (command.equals("play")) {
                Log.d(TAG, "onPlay trackNo " + trackNo);
                mediaPlayer = MediaPlayer.create(MusicService.this, playList[trackNo]);
                mediaPlayer.start();
            }
        }


        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }
}
