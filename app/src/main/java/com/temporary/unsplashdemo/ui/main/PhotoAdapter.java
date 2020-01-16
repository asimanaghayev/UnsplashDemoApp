package com.temporary.unsplashdemo.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.temporary.unsplashdemo.R;
import com.temporary.unsplashdemo.data.network.model.Photos;
import com.temporary.unsplashdemo.ui.search.ItemClickListener;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    List<Photos> mPhotos;

    private ItemClickListener itemClickListener;

    public PhotoAdapter() {

    }

    public PhotoAdapter(List<Photos> mPhotos, ItemClickListener itemClickListener) {
        this.mPhotos = mPhotos;
        this.itemClickListener = itemClickListener;
    }

    public void setItemClickListener(ItemClickListener<String> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setAdapterPhotos(List<Photos> mPhotos) {
        this.mPhotos = mPhotos;
    }

    public void addPhotos(List<Photos> photos) {
        if (mPhotos == null)
            this.mPhotos = photos;
        else
            this.mPhotos.addAll(photos);
    }

    public void clear() {
        mPhotos.clear();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (mPhotos.get(position) == null)
            return;
        Photos photo = mPhotos.get(position);

        if (photo.getUrls().getThumb() == null)
            return;

        Picasso.get().load(photo.getUrls().getThumb()).into(holder.imageView);
        holder.url = photo.getUrls().getRegular();
    }

    @Override
    public int getItemCount() {
        if (mPhotos == null)
            return 0;

        return mPhotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        String url;

        public ViewHolder(View view, final ItemClickListener itemClickListener) {
            super(view);
            imageView = view.findViewById(R.id.imageView);

            view.setOnClickListener(V -> {
                itemClickListener.onItemClick(url);
            });
        }


    }
}
