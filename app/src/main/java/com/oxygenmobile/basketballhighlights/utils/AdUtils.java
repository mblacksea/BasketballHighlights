package com.oxygenmobile.basketballhighlights.utils;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public final class AdUtils {

    public static void showInterstitialAd(Context context, String adUnitId) {
        InterstitialAd mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(adUnitId);
        mInterstitialAd.loadAd(new AdRequest.Builder()
                .addTestDevice("E034CB11F373623302D57C04E8A68BE4")
                .build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });
    }
}
