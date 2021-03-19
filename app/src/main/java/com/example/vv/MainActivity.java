package com.example.vv;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {


    /* ExoPlayer or 비디오뷰는 추후에 commit
    * 가로모드 , 비동기처리 , restful api
    *
    * raw 디렉토리의 mp4파일을 넣어서 진행하면 된다.
    * 또는 , 해당 url을 가지고 웹으로부터 가져와서 진행가능하다*/

    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vv = findViewById(R.id.vv);

        try {
            Uri web_videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
//            Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.replay);

            vv.setMediaController(new MediaController(this));
            vv.setVideoURI(web_videoUri);
            vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

        }catch (Exception e){
            Log.d("get", "printStackTrace: " + e.getMessage());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(vv!=null && vv.isPlaying()) {
            vv.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(vv!=null){
            vv.stopPlayback();
        }
    }
}