package com.oxygenmobile.basketballhighlights.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;

import com.onurkaganaldemir.ktoastlib.KToast;
import com.oxygenmobile.basketballhighlights.R;
import com.oxygenmobile.basketballhighlights.adapter.HighlightsAdapter;
import com.oxygenmobile.basketballhighlights.model.BasketballHighlightsUrl;
import com.oxygenmobile.basketballhighlights.model.Item;
import com.oxygenmobile.basketballhighlights.model.PlayListAPI;
import com.oxygenmobile.basketballhighlights.retrofit.APIClient;
import com.oxygenmobile.basketballhighlights.retrofit.APIInterface;
import com.oxygenmobile.basketballhighlights.utils.SessionOperation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HighlightsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlights);

        final BasketballHighlightsUrl basketballHighlightsUrl = SessionOperation.fetchFirebaseUrl(getApplicationContext());
        final String playListApi = basketballHighlightsUrl.getPlayListApi();

        this.fetchHightlightsItemsForRecyclerView(playListApi);

    }

    private void fetchHightlightsItemsForRecyclerView(String playListApi) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<PlayListAPI> playListCall = apiInterface.inquireNbaHighlightsPlayList(playListApi);
        playListCall.enqueue(new Callback<PlayListAPI>() {
            @Override
            public void onResponse(Call<PlayListAPI> call, Response<PlayListAPI> response) {
                final PlayListAPI body = response.body();
                final List<Item> items = body.getItems();

                generateRecyclerViewPlayList(items);

            }

            @Override
            public void onFailure(Call<PlayListAPI> call, Throwable t) {
                KToast.errorToast(HighlightsActivity.this, getString(R.string.toast_PlayListAPI_error), Gravity.BOTTOM, KToast.LENGTH_AUTO);
            }
        });

    }

    private void generateRecyclerViewPlayList(List<Item> items) {
        final RecyclerView recyclerView = findViewById(R.id.highlights_recycler_view);
        recyclerView.setHasFixedSize(true);

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        final HighlightsAdapter highlightsAdapter = new HighlightsAdapter(items, getApplicationContext());
        recyclerView.setAdapter(highlightsAdapter);
    }
}
