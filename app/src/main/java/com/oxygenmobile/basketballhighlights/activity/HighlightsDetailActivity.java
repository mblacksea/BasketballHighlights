package com.oxygenmobile.basketballhighlights.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.oxygenmobile.basketballhighlights.R;
import com.oxygenmobile.basketballhighlights.adapter.HighlightsDetailAdapter;
import com.oxygenmobile.basketballhighlights.model.Item;
import com.oxygenmobile.basketballhighlights.utils.AdUtils;

import java.util.List;

public class HighlightsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlights_detail);
        AdUtils.showBannerAd(findViewById(R.id.highlightsDetailAdview));

        final Intent intent = getIntent();
        final List<Item> highlightsDetail = (List<Item>) intent.getSerializableExtra(getString(R.string.intentHighlightsDetail));

        generateRecyclerViewPlayListDetail(highlightsDetail);
    }

    private void generateRecyclerViewPlayListDetail(List<Item> items) {
        final RecyclerView recyclerView = findViewById(R.id.highlights_detail_recycler_view);
        recyclerView.setHasFixedSize(true);

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        final HighlightsDetailAdapter highlightsAdapter = new HighlightsDetailAdapter(getApplicationContext(), items);
        recyclerView.setAdapter(highlightsAdapter);
    }
}
