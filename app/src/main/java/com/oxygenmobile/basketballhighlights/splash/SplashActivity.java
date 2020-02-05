package com.oxygenmobile.basketballhighlights.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oxygenmobile.basketballhighlights.R;
import com.oxygenmobile.basketballhighlights.model.BasketballHighlights;
import com.rbddevs.splashy.Splashy;

import android.content.Intent;

import com.oxygenmobile.basketballhighlights.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initialSplash();

        navigateToMainActivity();

    }

    private void initialSplash() {
        Splashy splashy = new Splashy(this);
        splashy.setTitle("Splashy");
        splashy.setLogo(R.mipmap.ic_launcher);
        splashy.setTime(3000);
        splashy.show();
    }

    private void navigateToMainActivity() {
        Intent toMainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(toMainActivity);
        finish();
    }
}
