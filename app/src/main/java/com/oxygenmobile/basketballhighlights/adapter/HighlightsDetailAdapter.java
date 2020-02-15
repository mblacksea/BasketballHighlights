package com.oxygenmobile.basketballhighlights.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oxygenmobile.basketballhighlights.R;
import com.oxygenmobile.basketballhighlights.activity.YoutubeDisplayActivity;
import com.oxygenmobile.basketballhighlights.model.Item;
import com.oxygenmobile.basketballhighlights.model.Snippet;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HighlightsDetailAdapter extends RecyclerView.Adapter<HighlightsDetailAdapter.DataObjectHolder> {

    private final static String TAG = "HighlightsDetailAdapter";
    private Context context;
    private List<Item> mDataset;

    public HighlightsDetailAdapter(Context context, List<Item> mDataset) {
        this.context = context;
        this.mDataset = mDataset;
    }

    static class DataObjectHolder extends RecyclerView.ViewHolder {
        private ImageView playListDetailImageView;
        private TextView textView;

        DataObjectHolder(View itemView) {
            super(itemView);
            playListDetailImageView = itemView.findViewById(R.id.playListDetailImageView);
            //textView = itemView.findViewById(R.id.gameName);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Item> getmDataset() {
        return mDataset;
    }

    public void setmDataset(List<Item> mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public HighlightsDetailAdapter.DataObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.highlights_detail_row, parent, false);
        return new HighlightsDetailAdapter.DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighlightsDetailAdapter.DataObjectHolder holder, int position) {
        Snippet snippet = mDataset.get(position).getSnippet();
        Picasso.get()
                .load(snippet.getThumbnails().getHigh().getUrl())
                .fit()
                .into(holder.playListDetailImageView);

        //holder.textView.setText("mustafa");

        holder.itemView.setOnClickListener(v -> {
            Log.e("videoId", snippet.getResourceId().getVideoId() + "Title: " + snippet.getTitle());
            navigateToYoutubeActivity(snippet.getResourceId().getVideoId());
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void navigateToYoutubeActivity(String videoId) {
        Intent toYoutubeActivity = new Intent(getContext(), YoutubeDisplayActivity.class);
        toYoutubeActivity.putExtra(getContext().getString(R.string.intentYoutubeDisplayVideoId), videoId);
        toYoutubeActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(toYoutubeActivity);
    }
}
