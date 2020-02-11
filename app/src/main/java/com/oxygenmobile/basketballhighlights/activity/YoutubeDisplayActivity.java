package com.oxygenmobile.basketballhighlights.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.oxygenmobile.basketballhighlights.R;

public class YoutubeDisplayActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_display);
    }
}
