package com.oxygenmobile.basketballhighlights.session;

import android.app.Application;

import com.oxygenmobile.basketballhighlights.model.BasketballHighlightsUrl;

public class GlobalVariables extends Application {

    private BasketballHighlightsUrl basketballHighlightsUrl;

    public BasketballHighlightsUrl getBasketballHighlightsUrl() {
        return basketballHighlightsUrl;
    }

    public void setBasketballHighlightsUrl(BasketballHighlightsUrl basketballHighlightsUrl) {
        this.basketballHighlightsUrl = basketballHighlightsUrl;
    }


}
