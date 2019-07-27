package com.myhexaville.login.Utils;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

public class SoundPlayer {
    public static void startPlaying(String fileName){
        MediaPlayer mPlayer= new MediaPlayer();
        String LOG_TAG="main";

   try {

       mPlayer.setDataSource(fileName);
       asyncPrepare(mPlayer);

   }catch(IOException e){
                        Log.e(LOG_TAG,"prepare() failed");
}
}

public static void stopPlaying(MediaPlayer Player){
                    Player.release();
                   Player =null;
    }
                   public static void asyncPrepare(MediaPlayer mediaPlayer) {
        // 准备：是操作硬件在播放，所以需要准备
        mediaPlayer.prepareAsync();

        // 监听异步准备，一旦准备完成，就会调用此方法
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlay) {
                // 调用此方法，代表异步准备完成✅
                // 开始播放
                mediaPlay.start();

            }
        });
    }
}


