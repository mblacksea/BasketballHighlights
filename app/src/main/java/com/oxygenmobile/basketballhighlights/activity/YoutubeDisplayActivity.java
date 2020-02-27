package com.oxygenmobile.basketballhighlights.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.onurkaganaldemir.ktoastlib.KToast;
import com.oxygenmobile.basketballhighlights.R;
import com.oxygenmobile.basketballhighlights.utils.Constants;


public class YoutubeDisplayActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private String videoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        videoId = intent.getStringExtra(getString(R.string.intentYoutubeDisplayVideoId));
        setContentView(R.layout.activity_youtube_display);

        YouTubePlayerView mPlayerView = findViewById(R.id.youtube_view);
        mPlayerView.initialize(Constants.YOUTUBE_API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        youTubePlayer.loadVideo(videoId);
        youTubePlayer.setFullscreen(true);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            KToast.errorToast(YoutubeDisplayActivity.this, getString(R.string.youtubePlayerError), Gravity.BOTTOM, KToast.LENGTH_AUTO);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
