package com.absathe.gravitate.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.absathe.gravitate.R;
import com.absathe.gravitate.items.InstaItem;
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

public class InstaItemAdapter extends RecyclerView.Adapter<InstaItemAdapter.ViewHolder> {
    private List<InstaItem> instaItemList = null;
    public void setInstaItemList(List<InstaItem> list) {
        this.instaItemList = list;
    }
    @Override
    public InstaItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.instaitem, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InstaItem item = instaItemList.get(position);
        final ImageView loading = holder.loadingImage;
        final ImageView main = holder.mainImage;
        Context context = holder.itemView.getContext();
        System.out.println("I was called and I have the context " +  context);
        item.setImageURL("https://scontent-frx5-1.cdninstagram.com/vp/69df18ad0c053b6d351e8d304005ac2e/5B57525A/t51.2885-15/e35/29740826_214711292448789_8802688726175055872_n.jpg");
        Glide.with(context)
                .load(item.getImageURL())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        loading.setVisibility(View.VISIBLE);
                        main.setVisibility(View.GONE);
                        loading.setImageResource(R.drawable.error);
                        System.out.println("I  recieved exception e" + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        loading.setVisibility(View.GONE);
                        main.setVisibility(View.VISIBLE);
                        System.out.println("I'm done loading this image");
                        return false;
                    }
                })
                .apply(RequestOptions.centerCropTransform())
                .into(main);

    }

    @Override
    public int getItemCount() {
        return instaItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mainImage;
        private ImageView loadingImage;
        public ViewHolder(View view) {
            super(view);
            mainImage = view.findViewById(R.id.instaitem_picture);
            loadingImage = view.findViewById(R.id.instaitem_loading);
        }
    }
}
