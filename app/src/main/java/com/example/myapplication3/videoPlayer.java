package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class videoPlayer extends YouTubeBaseActivity {
    public  String id;
    private YouTubePlayerView youTubePlayerView;
    private  YouTubePlayer player;
    private  Button button;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        id=b.getString("id");

        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtube);
        //button=(Button)findViewById(R.id.button);

        onInitializedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                player=youTubePlayer;
                player.loadVideo(id);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }

        };
        YouTubePlayer.OnFullscreenListener y=new YouTubePlayer.OnFullscreenListener() {
            @Override
            public void onFullscreen(boolean b) {
                player.setFullscreen(b);
            }
        };


        youTubePlayerView.initialize(Youtube_key.getApi_key(),onInitializedListener);
    }
}