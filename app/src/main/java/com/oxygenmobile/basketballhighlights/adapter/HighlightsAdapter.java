package com.oxygenmobile.basketballhighlights.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oxygenmobile.basketballhighlights.R;
import com.oxygenmobile.basketballhighlights.model.BasketballHighlightsUrl;
import com.oxygenmobile.basketballhighlights.model.Item;
import com.oxygenmobile.basketballhighlights.model.PlayListAPI;
import com.oxygenmobile.basketballhighlights.retrofit.APIClient;
import com.oxygenmobile.basketballhighlights.retrofit.APIInterface;
import com.oxygenmobile.basketballhighlights.utils.SessionOperation;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HighlightsAdapter extends RecyclerView.Adapter<HighlightsAdapter.DataObjectHolder> {

    private final static String TAG = "HighlightsAdapter";
    private Context context;
    private List<Item> mDataset;

    public HighlightsAdapter(List<Item> mDataset, Context context) {
        this.mDataset = mDataset;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    static class DataObjectHolder extends RecyclerView.ViewHolder {
        private ImageView playListImageView;

        DataObjectHolder(View itemView) {
            super(itemView);
            playListImageView = itemView.findViewById(R.id.playListImageView);
        }
    }

    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.highlights_row, parent, false);
        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {
        Picasso.get()
                .load(mDataset.get(position).getSnippet().getThumbnails().getHigh().getUrl())
                .fit()
                .into(holder.playListImageView);

        holder.playListImageView.setOnClickListener(view -> {
            final BasketballHighlightsUrl basketballHighlightsUrl = SessionOperation.fetchFirebaseUrl(getContext());
            final String playListVideoUrl = basketballHighlightsUrl.getPlayListVideo();

            final APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            final Call<PlayListAPI> playListVideoCall = apiInterface.inquireNbaHighlightsPlayListById(playListVideoUrl, mDataset.get(position).getId());
            playListVideoCall.enqueue(new Callback<PlayListAPI>() {
                @Override
                public void onResponse(Call<PlayListAPI> call, Response<PlayListAPI> response) {
                    Log.e(TAG, String.valueOf(response.code()));
                }

                @Override
                public void onFailure(Call<PlayListAPI> call, Throwable t) {
                    Log.e(TAG, "Error an occurred" + t.getMessage() + " " + t.getLocalizedMessage());
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
