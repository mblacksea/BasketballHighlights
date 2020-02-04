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

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //initialSplash();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("basketballHighlights");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BasketballHighlights model = dataSnapshot.getValue(BasketballHighlights.class);
                System.out.println(model.getPlayListApi() + model.getPlayListVideo() + model.getTopPlayList());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    private void initialSplash() {
        Splashy splashy = new Splashy(this);
        splashy.setTitle("Splashy");
        splashy.setLogo(R.mipmap.ic_launcher);
        splashy.show();
    }
}
