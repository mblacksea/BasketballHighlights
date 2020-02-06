package com.oxygenmobile.basketballhighlights.splash;

import androidx.annotation.NonNull;

import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oxygenmobile.basketballhighlights.R;
import com.oxygenmobile.basketballhighlights.model.BasketballHighlights;
import com.oxygenmobile.basketballhighlights.session.GlobalVariables;

import android.content.Intent;
import android.util.Log;

import com.oxygenmobile.basketballhighlights.MainActivity;
import com.oxygenmobile.basketballhighlights.utils.Constants;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


public class SplashActivity extends AwesomeSplash {

    private final static String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        initialSplash();

        navigateToMainActivity();

    }

    @Override
    public void initSplash(ConfigSplash configSplash) {
        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.colorAccent); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.ic_launcher); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        //configSplash.setPathSplash(Constants.DROID_LOGO); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.colorAccent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(3000);
        configSplash.setPathSplashFillColor(R.color.colorAccent); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("My Awesome App");
        configSplash.setTitleTextColor(R.color.colorAccent);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        //configSplash.setTitleFont("fonts/myfont.ttf"); //provide string to your font located in assets/fonts/
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
                final BasketballHighlights basketballHighlightsUrl = dataSnapshot.getValue(BasketballHighlights.class);
                ((GlobalVariables) getApplicationContext()).setBasketballHighlightsUrl(basketballHighlightsUrl);

                navigateToMainActivity();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "The read data failed from Firebase" + databaseError.getCode() + " " + databaseError.getMessage() + " " + databaseError.getDetails());
                //TODO
                //Toast message find awesome library!
            }
        });
    }

    private void navigateToMainActivity() {
        Intent toMainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(toMainActivity);
        finish();
    }
}