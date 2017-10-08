package com.omikronsoft.differentcolor.control;

import android.content.Context;
import android.media.MediaPlayer;

import com.omikronsoft.differentcolor.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public class AudioController {
    private static final Map<AudioClip, MediaPlayer> CACHED_PLAYERS = new HashMap<>();

    public static void play(Context context, AudioClip audioClip, boolean soundEnabled){
        if(soundEnabled){
            MediaPlayer mp = getPlayer(audioClip, context);

            // TODO fix sound play delay
            mp.seekTo(0);
            mp.start();
        }
    }

    private static MediaPlayer getPlayer(AudioClip clip, Context context){
        synchronized (CACHED_PLAYERS){
            if(!CACHED_PLAYERS.containsKey(clip)){
                CACHED_PLAYERS.put(clip, MediaPlayer.create(context, clip.getRaw()));
            }
        }
        return CACHED_PLAYERS.get(clip);
    }
}
