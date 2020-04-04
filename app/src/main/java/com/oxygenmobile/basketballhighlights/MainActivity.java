package com.oxygenmobile.basketballhighlights;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Patterns;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oxygenmobile.basketballhighlights.activity.HighlightsActivity;
import com.oxygenmobile.basketballhighlights.activity.ShaqtinAFoolActivity;
import com.oxygenmobile.basketballhighlights.activity.Top10PlaysActivity;
import com.oxygenmobile.basketballhighlights.model.Suggestion;
import com.oxygenmobile.basketballhighlights.utils.AdUtils;
import com.oxygenmobile.basketballhighlights.utils.Constants;
import com.oxygenmobile.basketballhighlights.utils.NavigateUtils;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements RatingDialogListener {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor myEdit;

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
        AdUtils.showBannerAd(findViewById(R.id.mainActivityAdView));

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE, MODE_PRIVATE);
        boolean checkReminder = sharedPreferences.getBoolean(Constants.CHECK_REMINDER, true);
        if (checkReminder) {
            int rateAppReminderCount = sharedPreferences.getInt(Constants.RATE_APP_REMINDER, 0);
            if (rateAppReminderCount == 3) {
                showRatingDialog();
            } else {
                myEdit = sharedPreferences.edit();
                myEdit.putInt(Constants.RATE_APP_REMINDER, rateAppReminderCount + 1);
                myEdit.apply();
            }
        }
    }

    @Override
    public void onNegativeButtonClicked() {
        // we can never show rate app dialog.
        blockRateAppDialog();
    }

    @Override
    public void onNeutralButtonClicked() {
        //do nothing.
    }

    @Override
    public void onPositiveButtonClicked(int star, String comment) {
        String id = findUniqueUserId();
        DatabaseReference mDatabaseRead = FirebaseDatabase.getInstance().getReference();
        mDatabaseRead.child(Constants.FIREBASE_SUGGESTIONS).push().setValue(new Suggestion(id, star, comment));
        blockRateAppDialog();
        rateMe();
    }

    private void blockRateAppDialog() {
        myEdit = sharedPreferences.edit();
        myEdit.putBoolean(Constants.CHECK_REMINDER, false);
        myEdit.apply();
    }

    @SuppressLint("HardwareIds")
    private String findUniqueUserId() {
        return Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    private void rateMe() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + this.getPackageName())));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }

    private void showRatingDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Never")
                .setNeutralButtonText("Later")
                .setDefaultRating(5)
                .setNumberOfStars(5)
                .setTitle("Rate this application")
                .setDescription("Please select some stars and give your feedback")
                .setStarColor(R.color.starColor)
                .setNoteDescriptionTextColor(R.color.noteDescriptionTextColor)
                .setTitleTextColor(R.color.titleTextColor)
                .setDescriptionTextColor(R.color.descriptionTextColor)
                .setCommentTextColor(R.color.commentTextColor)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .setWindowAnimation(R.style.MyDialogSlideHorizontalAnimation)
                .setHint("Please write your comment here ...")
                .setHintTextColor(R.color.descriptionTextColor)
                .setCancelable(false)
                .setDefaultComment(R.string.defaultComment)
                .setCanceledOnTouchOutside(false)
                .create(MainActivity.this)
                .show();
    }

}
