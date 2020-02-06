package com.oxygenmobile.basketballhighlights.session;

import android.app.Application;

import com.oxygenmobile.basketballhighlights.model.BasketballHighlights;

public class GlobalVariables extends Application {

    private BasketballHighlights basketballHighlightsUrl;

    public BasketballHighlights getBasketballHighlightsUrl() {
        return basketballHighlightsUrl;
    }

    public void setBasketballHighlightsUrl(BasketballHighlights basketballHighlightsUrl) {
        this.basketballHighlightsUrl = basketballHighlightsUrl;
    }
}
