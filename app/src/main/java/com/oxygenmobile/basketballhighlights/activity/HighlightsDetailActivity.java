package com.oxygenmobile.basketballhighlights.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.oxygenmobile.basketballhighlights.R;
import com.oxygenmobile.basketballhighlights.adapter.HighlightsAdapter;
import com.oxygenmobile.basketballhighlights.model.Item;
import com.oxygenmobile.basketballhighlights.model.PlayListAPI;

import java.util.List;
import java.util.Objects;

public class HighlightsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlights_detail);

        final Intent intent = getIntent();
        final PlayListAPI highlightsDetail = (PlayListAPI) intent.getSerializableExtra(getString(R.string.intentHighlightsDetail));
        final List<Item> highlightsDetailItems = Objects.requireNonNull(highlightsDetail).getItems();

        generateRecyclerViewPlayListDetail(highlightsDetailItems);
    }

    private void generateRecyclerViewPlayListDetail(List<Item> items) {
        final RecyclerView recyclerView = findViewById(R.id.highlights_detail_recycler_view);
        recyclerView.setHasFixedSize(true);

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        final HighlightsAdapter highlightsAdapter = new HighlightsAdapter(getApplicationContext(), items);
        recyclerView.setAdapter(highlightsAdapter);
    }
}
