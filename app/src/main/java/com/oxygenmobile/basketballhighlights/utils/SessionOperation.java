package com.oxygenmobile.basketballhighlights.utils;

import android.content.Context;

import com.oxygenmobile.basketballhighlights.model.BasketballHighlightsUrl;
import com.oxygenmobile.basketballhighlights.model.Item;
import com.oxygenmobile.basketballhighlights.session.GlobalVariables;

import java.util.List;

public final class SessionOperation {

    public static void saveFirabaseUrl(Context context, BasketballHighlightsUrl basketballHighlightsUrl) {
        ((GlobalVariables) context).setBasketballHighlightsUrl(basketballHighlightsUrl);
    }

    public static BasketballHighlightsUrl fetchFirebaseUrl(Context context) {
        return ((GlobalVariables) context).getBasketballHighlightsUrl();
    }

    public static void saveHighlights(Context context, List<Item> highlights) {
        ((GlobalVariables) context).setHighlights(highlights);
    }

    public static List<Item> fetchHighlights(Context context) {
        return ((GlobalVariables) context).getHighlights();
    }

}
