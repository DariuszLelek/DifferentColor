package com.omikronsoft.differentcolor.control;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.SoundEffectConstants;

import java.io.IOException;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public class AudioController {

    public static void play(Context context, AudioClip audioClip, boolean soundEnabled){
        if(soundEnabled){
            playClipInThread(context, audioClip);
        }
    }

    private static void playClipInThread(final Context context, final AudioClip clip){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MediaPlayer mp = MediaPlayer.create(context, clip.getRaw());
                mp.start();

                try {
                    Thread.sleep(mp.getDuration());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    releaseMp(mp);
                }
            }
        }).start();
    }

    private static void releaseMp(MediaPlayer mp){
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
