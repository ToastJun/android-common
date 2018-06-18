package com.toast.common.utils;

import android.app.Service;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 *
 * @author 47066
 * @date 2017/10/9
 */

public class VoiceUtils {
    public static MediaPlayer mp;


    public static void playMp3(int mp3) {
        // 首先加大音量
        AudioManager audiomanager = (AudioManager) Utils.getContext().getSystemService(Service.AUDIO_SERVICE);
        audiomanager.setMode(AudioManager.MODE_NORMAL);
        audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,
                audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC), 0);
        audiomanager.adjustVolume(AudioManager.ADJUST_SAME, 0);


//        Intent intent = new Intent(Utils.getContext(), VoiceIntentService.class);
//        intent.putExtra(VoiceIntentService.VOICE_RES_ID,mp3);
//        Utils.getContext().startService(intent);



        // 播放raw里面的mp3文件
//        MediaPlayer mediaPlayer = MediaPlayer.create(Utils.getContext(),
//                mp3);
//        try {
//            //mediaPlayer.prepare();
//            mediaPlayer.setLooping(false);
//            mediaPlayer.start();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }




//        List<SongInfo> music = new ArrayList<>();
//        SongInfo info = new SongInfo();
//        info.setSongUrl(mp3);
//
//        MusicManager.get().playMusic(music, 0 ,true);


        if (true) {
            try {
                if (mp == null) {
                    mp = new MediaPlayer();
                }
                mp.reset();
                AssetFileDescriptor afd = Utils.getContext().getResources().openRawResourceFd(mp3);
                if (afd == null)  return;
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();

                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mp.start();
        }
    }
}