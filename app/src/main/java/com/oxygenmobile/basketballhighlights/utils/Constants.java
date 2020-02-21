package com.oxygenmobile.basketballhighlights.utils;

import com.oxygenmobile.basketballhighlights.R;

import id.arieridwan.lib.PageLoader;

public final class Constants {
    public final static String FIREBASE_BASE_URL = "basketballHighlights";
    public final static String API_BASE_URL = "https://www.googleapis.com/youtube/v3/";
    public final static String YOUTUBE_API_KEY = "AIzaSyAb7oyM3q5Z825PTIlnJd-fFxBxLtmzK9I";

    public static void showProgressDialog(PageLoader pageLoader) {
        pageLoader.setLoadingImageHeight(80);
        pageLoader.setLoadingImageWidth(80);
        pageLoader.startProgress();
    }
}
