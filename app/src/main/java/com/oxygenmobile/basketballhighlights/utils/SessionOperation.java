package com.oxygenmobile.basketballhighlights.utils;

import android.content.Context;

import com.oxygenmobile.basketballhighlights.model.BasketballHighlightsUrl;
import com.oxygenmobile.basketballhighlights.session.GlobalVariables;

public final class SessionOperation {

    public static void saveFirabaseUrl(Context context, BasketballHighlightsUrl basketballHighlightsUrl) {
        ((GlobalVariables) context).setBasketballHighlightsUrl(basketballHighlightsUrl);
    }

    public static BasketballHighlightsUrl fetchFirebaseUrl(Context context) {
        return ((GlobalVariables) context).getBasketballHighlightsUrl();
    }

}
