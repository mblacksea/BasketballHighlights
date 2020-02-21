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
import com.oxygenmobile.basketballhighlights.utils.NavigateUtils;
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
        private TextView playListDetailTitle;

        DataObjectHolder(View itemView) {
            super(itemView);
            playListDetailImageView = itemView.findViewById(R.id.playListDetailImageView);
            playListDetailTitle = itemView.findViewById(R.id.playListDetailTitle);
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
        String photoUrl = snippet.getThumbnails().getMedium() != null ? snippet.getThumbnails().getMedium().getUrl() : snippet.getThumbnails().getDefault().getUrl();
        Picasso.get()
                .load(photoUrl)
                .fit()
                .into(holder.playListDetailImageView);

        holder.playListDetailTitle.setText(snippet.getTitle());
        //holder.textView.setText("mustafa");

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
