package com.oxygenmobile.basketballhighlights.session;

import android.app.Application;

import com.oxygenmobile.basketballhighlights.model.BasketballHighlightsUrl;
import com.oxygenmobile.basketballhighlights.model.Item;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariables extends Application {

    private BasketballHighlightsUrl basketballHighlightsUrl;
    private List<Item> highlights = new ArrayList<>();

    public BasketballHighlightsUrl getBasketballHighlightsUrl() {
        return basketballHighlightsUrl;
    }

    public void setBasketballHighlightsUrl(BasketballHighlightsUrl basketballHighlightsUrl) {
        this.basketballHighlightsUrl = basketballHighlightsUrl;
    }

    public List<Item> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<Item> highlights) {
        this.highlights = highlights;
    }
}
