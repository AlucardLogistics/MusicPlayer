package com.android.alucard.musicplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TestBroacast extends BroadcastReceiver  {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("key");
        Toast.makeText(context, "MusicPlayer " + message, Toast.LENGTH_SHORT).show();
    }
}
