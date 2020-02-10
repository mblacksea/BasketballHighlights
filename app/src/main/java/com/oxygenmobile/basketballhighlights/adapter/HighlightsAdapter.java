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
import com.oxygenmobile.basketballhighlights.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;


public class HighlightsAdapter extends RecyclerView.Adapter<HighlightsAdapter.DataObjectHolder> {

    private final static String TAG = "HighlightsAdapter";
    private Context context;
    private List<Item> mDataset;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public HighlightsAdapter(List<Item> mDataset, Context context) {
        this.mDataset = mDataset;
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
        //holder.imageView.setText(mDataset.get(position).getSnippet().getTitle());
        Picasso.get()
                .load(mDataset.get(position).getSnippet().getThumbnails().getHigh().getUrl())
                .fit()
                .into(holder.playListImageView);

        holder.playListImageView.setOnClickListener(view -> {
            //TODO tıklandıktan sonra yapılacaklar!
            Log.e(TAG, mDataset.get(position).getId());
           /* Intent intent = new Intent(context, PlayListItemsActivity.class);
            intent.putExtra("PlayListID", position);
            context.startActivity(intent);*/
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
