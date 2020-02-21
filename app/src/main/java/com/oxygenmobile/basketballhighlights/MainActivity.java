package com.oxygenmobile.basketballhighlights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;

import com.onurkaganaldemir.ktoastlib.KToast;
import com.oxygenmobile.basketballhighlights.activity.HighlightsActivity;
import com.oxygenmobile.basketballhighlights.activity.Top10PlaysActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    @OnClick(R.id.highlightsPlays)
    void onHighlightsPlays() {
        Intent intent = new Intent(getApplicationContext(), HighlightsActivity.class);
        startActivity(intent);
        KToast.successToast(MainActivity.this, "Highlights on click", Gravity.BOTTOM, KToast.LENGTH_AUTO);
    }

    @OnClick(R.id.topTenPlays)
    void onTopTenPlays() {
        Intent intent = new Intent(getApplicationContext(), Top10PlaysActivity.class);
        startActivity(intent);
        KToast.successToast(MainActivity.this, "Top 10 Plays on click", Gravity.BOTTOM, KToast.LENGTH_AUTO);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

}
