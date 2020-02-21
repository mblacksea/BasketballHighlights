package com.oxygenmobile.basketballhighlights.adapter;

import android.content.Context;
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
import com.oxygenmobile.basketballhighlights.utils.NavigateUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Top10PlaysAdapter extends RecyclerView.Adapter<Top10PlaysAdapter.DataObjectHolder> {

    private final static String TAG = "Top10PlaysAdapter";
    private Context context;
    private List<Item> mDataset;

    public Top10PlaysAdapter(Context context, List<Item> mDataset) {
        this.context = context;
        this.mDataset = mDataset;
    }

    private Context getContext() {
        return context;
    }

    static class DataObjectHolder extends RecyclerView.ViewHolder {
        private ImageView top10PlaysImageView;
        private TextView top10PlaysTitleTextView;

        DataObjectHolder(View itemView) {
            super(itemView);
            top10PlaysImageView = itemView.findViewById(R.id.top10PlaysImageView);
            top10PlaysTitleTextView = itemView.findViewById(R.id.top10PlaysTitleTextView);
        }
    }

    @NonNull
    @Override
    public Top10PlaysAdapter.DataObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top10plays_row, parent, false);
        return new Top10PlaysAdapter.DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Top10PlaysAdapter.DataObjectHolder holder, int position) {
        final Snippet snippet = mDataset.get(position).getSnippet();
        Picasso.get()
                .load(snippet.getThumbnails().getHigh().getUrl() != null ? snippet.getThumbnails().getHigh().getUrl() : snippet.getThumbnails().getDefault().getUrl())
                .fit()
                .into(holder.top10PlaysImageView);

        holder.top10PlaysTitleTextView.setText(mDataset.get(position).getSnippet().getTitle());

        holder.itemView.setOnClickListener(v -> {
            Log.e("videoId", snippet.getResourceId().getVideoId() + "Title: " + snippet.getTitle());
            NavigateUtils.navigateToActivity(getContext(), context.getString(R.string.intentYoutubeDisplayVideoId), snippet.getResourceId().getVideoId(), YoutubeDisplayActivity.class);
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
