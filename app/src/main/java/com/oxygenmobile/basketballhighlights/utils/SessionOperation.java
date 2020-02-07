package com.oxygenmobile.basketballhighlights.utils;

import android.content.Context;

import com.oxygenmobile.basketballhighlights.model.BasketballHighlights;
import com.oxygenmobile.basketballhighlights.session.GlobalVariables;

public final class SessionOperation {

    public static void saveFirabaseUrl(Context context, BasketballHighlights basketballHighlightsUrl) {
        ((GlobalVariables) context).setBasketballHighlightsUrl(basketballHighlightsUrl);
    }

    public static BasketballHighlights fetchFirebaseUrl(Context context) {
        return ((GlobalVariables) context).getBasketballHighlightsUrl();
    }

}
