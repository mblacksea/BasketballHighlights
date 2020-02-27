package com.oxygenmobile.basketballhighlights;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.oxygenmobile.basketballhighlights.activity.HighlightsActivity;
import com.oxygenmobile.basketballhighlights.activity.ShaqtinAFoolActivity;
import com.oxygenmobile.basketballhighlights.activity.Top10PlaysActivity;
import com.oxygenmobile.basketballhighlights.utils.NavigateUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.highlightsPlays)
    void onHighlightsPlays() {
        NavigateUtils.navigateToActivityWithoutValue(getApplicationContext(), HighlightsActivity.class);
    }

    @OnClick(R.id.topTenPlays)
    void onTopTenPlays() {
        NavigateUtils.navigateToActivityWithoutValue(getApplicationContext(), Top10PlaysActivity.class);
    }

    @OnClick(R.id.shaqtinAFool)
    void onShaqtinAFool() {
        NavigateUtils.navigateToActivityWithoutValue(getApplicationContext(), ShaqtinAFoolActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

}
