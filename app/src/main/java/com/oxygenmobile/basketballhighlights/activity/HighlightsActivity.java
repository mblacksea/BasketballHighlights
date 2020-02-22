package com.oxygenmobile.basketballhighlights.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.oxygenmobile.basketballhighlights.R;
import com.oxygenmobile.basketballhighlights.adapter.HighlightsAdapter;
import com.oxygenmobile.basketballhighlights.model.Item;
import com.oxygenmobile.basketballhighlights.utils.AdUtils;
import com.oxygenmobile.basketballhighlights.utils.SessionOperation;

import java.util.List;

public class HighlightsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlights);

        AdUtils.showBannerAd(findViewById(R.id.highlightsAdView));

        generateHighlights();
    }

    private void generateHighlights() {
        final List<Item> highlights = SessionOperation.fetchHighlights(getApplicationContext());
        this.generateRecyclerViewPlayList(highlights);

    }

    private void generateRecyclerViewPlayList(List<Item> items) {
        final RecyclerView recyclerView = findViewById(R.id.highlights_recycler_view);
        recyclerView.setHasFixedSize(true);

        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(layoutManager);

        final HighlightsAdapter highlightsAdapter = new HighlightsAdapter(getApplicationContext(), items);
        recyclerView.setAdapter(highlightsAdapter);
    }
}
