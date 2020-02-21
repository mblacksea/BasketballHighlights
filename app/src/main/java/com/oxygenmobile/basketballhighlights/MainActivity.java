package com.oxygenmobile.basketballhighlights;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;

import com.onurkaganaldemir.ktoastlib.KToast;
import com.oxygenmobile.basketballhighlights.activity.HighlightsActivity;
import com.oxygenmobile.basketballhighlights.activity.Top10PlaysActivity;
import com.oxygenmobile.basketballhighlights.utils.NavigateUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.highlightsPlays)
    void onHighlightsPlays() {
        NavigateUtils.navigateToActivityWithoutValue(getApplicationContext(), HighlightsActivity.class);
        KToast.successToast(MainActivity.this, "Highlights on click", Gravity.BOTTOM, KToast.LENGTH_AUTO);
    }

    @OnClick(R.id.topTenPlays)
    void onTopTenPlays() {
        NavigateUtils.navigateToActivityWithoutValue(getApplicationContext(), Top10PlaysActivity.class);
        KToast.successToast(MainActivity.this, "Top 10 Plays on click", Gravity.BOTTOM, KToast.LENGTH_AUTO);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

}
