package com.oxygenmobile.basketballhighlights.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oxygenmobile.basketballhighlights.R;
import com.oxygenmobile.basketballhighlights.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HighlightsDetailAdapter extends RecyclerView.Adapter<HighlightsDetailAdapter.DataObjectHolder> {

    private final static String TAG = "HighlightsDetailAdapter";
    private Context context;
    private List<Item> mDataset;

    static class DataObjectHolder extends RecyclerView.ViewHolder {
        private ImageView playListDetailImageView;

        DataObjectHolder(View itemView) {
            super(itemView);
            playListDetailImageView = itemView.findViewById(R.id.playListDetailImageView);
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
        Picasso.get()
                .load(mDataset.get(position).getSnippet().getThumbnails().getHigh().getUrl())
                .fit()
                .into(holder.playListDetailImageView);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
