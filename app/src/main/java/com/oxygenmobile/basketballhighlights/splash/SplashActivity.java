package com.oxygenmobile.basketballhighlights.splash;

import androidx.annotation.NonNull;


import com.daimajia.androidanimations.library.Techniques;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onurkaganaldemir.ktoastlib.KToast;
import com.oxygenmobile.basketballhighlights.R;
import com.oxygenmobile.basketballhighlights.model.BasketballHighlightsUrl;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;

import com.oxygenmobile.basketballhighlights.MainActivity;
import com.oxygenmobile.basketballhighlights.model.Item;
import com.oxygenmobile.basketballhighlights.model.PlayListAPI;
import com.oxygenmobile.basketballhighlights.retrofit.APIClient;
import com.oxygenmobile.basketballhighlights.retrofit.APIInterface;
import com.oxygenmobile.basketballhighlights.utils.Constants;
import com.oxygenmobile.basketballhighlights.utils.SessionOperation;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashActivity extends AwesomeSplash {

    private final static String TAG = "SplashActivity";

    @Override
    public void initSplash(ConfigSplash configSplash) {
        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.splashBackground); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.ic_launcher); //or any other drawable
        configSplash.setAnimLogoSplashDuration(1000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeIn); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        //configSplash.setPathSplash(Constants.DROID_LOGO); //set path String
        /*configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(1000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.colorAccent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(3000);
        configSplash.setPathSplashFillColor(R.color.colorAccent);*/ //path object filling color


        //Customize Title
        configSplash.setTitleSplash("");
    }

    @Override
    public void animationsFinished() {
        inquireFirebaseUrl();
    }

    private void inquireFirebaseUrl() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference(Constants.FIREBASE_BASE_URL);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final BasketballHighlightsUrl basketballHighlightsUrl = dataSnapshot.getValue(BasketballHighlightsUrl.class);
                SessionOperation.saveFirabaseUrl(getApplicationContext(), basketballHighlightsUrl);
                fetchHightlightsItems(basketballHighlightsUrl != null ? basketballHighlightsUrl.getPlayListApi() : null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "The read data failed from Firebase" + databaseError.getCode() + " " + databaseError.getMessage() + " " + databaseError.getDetails());
                KToast.warningToast(SplashActivity.this, getString(R.string.toast_firebase_error), Gravity.BOTTOM, KToast.LENGTH_AUTO);
            }
        });
    }

    private void fetchHightlightsItems(String playListApi) {
        final APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        final Call<PlayListAPI> playListCall = apiInterface.inquireNbaAllHighlightsPlayList(playListApi);
        playListCall.enqueue(new Callback<PlayListAPI>() {
            @Override
            public void onResponse(Call<PlayListAPI> call, Response<PlayListAPI> response) {
                final PlayListAPI body = response.body();
                final List<Item> items = body.getItems();
                SessionOperation.saveHighlights(getApplicationContext(), items);
                navigateToMainActivity();
            }

            @Override
            public void onFailure(Call<PlayListAPI> call, Throwable t) {
                KToast.errorToast(SplashActivity.this, getString(R.string.toast_PlayListAPI_error), Gravity.BOTTOM, KToast.LENGTH_AUTO);
            }
        });
    }

    private void navigateToMainActivity() {
        Intent toMainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(toMainActivity);
        finish();
    }
}
