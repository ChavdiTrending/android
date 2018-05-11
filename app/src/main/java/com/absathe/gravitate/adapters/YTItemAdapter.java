package com.absathe.gravitate.adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.absathe.gravitate.R;
import com.absathe.gravitate.YTFragment;
import com.absathe.gravitate.items.YTItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by ABSathe on 19-04-2018.
 */

public class YTItemAdapter extends RecyclerView.Adapter<YTItemAdapter.ViewHolder> {
    private List<YTItem> ytItemList;
    public void setYTItemList(List<YTItem> list) {
        this.ytItemList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ytitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final YTItem item = ytItemList.get(position);
        final ImageView thumbnail = holder.thumbnail;
        TextView title = holder.title;
        Button open_in_app = holder.open_in_app;
        Button open_in_browser = holder.open_in_browser;

        Glide.with(thumbnail.getContext())
                .load(item.getThumnailURL())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        thumbnail.setImageResource(R.drawable.error);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .apply(RequestOptions.centerCropTransform())
                .into(thumbnail);
        title.setText(item.getVideoTitle());
        open_in_browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YTFragment.openInBrowser(item.getVideoURL());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ytItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView thumbnail;
        private TextView title;
        private Button open_in_app;
        private Button open_in_browser;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.ytitem_title);
            thumbnail = itemView.findViewById(R.id.ytitem_thumbnail);
            open_in_app = itemView.findViewById(R.id.ytitem_open_in_app);
            open_in_browser = itemView.findViewById(R.id.ytitem_open_in_browser);
        }
    }
}
