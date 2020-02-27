package com.oxygenmobile.basketballhighlights.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Gravity;

import com.onurkaganaldemir.ktoastlib.KToast;
import com.oxygenmobile.basketballhighlights.R;
import com.oxygenmobile.basketballhighlights.adapter.Top10PlaysAdapter;
import com.oxygenmobile.basketballhighlights.model.BasketballHighlightsUrl;
import com.oxygenmobile.basketballhighlights.model.Item;
import com.oxygenmobile.basketballhighlights.model.PlayListAPI;
import com.oxygenmobile.basketballhighlights.retrofit.APIClient;
import com.oxygenmobile.basketballhighlights.retrofit.APIInterface;
import com.oxygenmobile.basketballhighlights.utils.AdUtils;
import com.oxygenmobile.basketballhighlights.utils.Constants;
import com.oxygenmobile.basketballhighlights.utils.SessionOperation;

import java.util.List;

import id.arieridwan.lib.PageLoader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShaqtinAFoolActivity extends AppCompatActivity {

    private PageLoader pageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaqtin_afool);
        pageLoader = findViewById(R.id.pageloader);
        Constants.showProgressDialog(pageLoader);

        AdUtils.showBannerAd(findViewById(R.id.shaqtinAFoolAdView));

        final BasketballHighlightsUrl basketballHighlightsUrl = SessionOperation.fetchFirebaseUrl(getApplicationContext());
        final String shaqtinAFool = basketballHighlightsUrl.getShaqtinAFool();

        this.fetchTop10ItemsForRecyclerView(shaqtinAFool);

    }

    private void fetchTop10ItemsForRecyclerView(String shaqtinAFool) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<PlayListAPI> playListCall = apiInterface.inquireTop10PlayList(shaqtinAFool);
        playListCall.enqueue(new Callback<PlayListAPI>() {
            @Override
            public void onResponse(Call<PlayListAPI> call, Response<PlayListAPI> response) {
                final PlayListAPI body = response.body();
                final List<Item> items = body.getItems();

                generateRecyclerViewShaqtinAFool(items);

            }

            @Override
            public void onFailure(Call<PlayListAPI> call, Throwable t) {
                KToast.errorToast(ShaqtinAFoolActivity.this, getString(R.string.toast_Top10PlaysAPI_error), Gravity.BOTTOM, KToast.LENGTH_AUTO);
                pageLoader.stopProgress();
            }
        });
    }

    private void generateRecyclerViewShaqtinAFool(List<Item> items) {
        final RecyclerView recyclerView = findViewById(R.id.shaqtinAFool_recycler_view);
        recyclerView.setHasFixedSize(true);

        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(layoutManager);

        final Top10PlaysAdapter top10PlaysAdapter = new Top10PlaysAdapter(getApplicationContext(), items);
        recyclerView.setAdapter(top10PlaysAdapter);

        pageLoader.stopProgress();
    }
}
