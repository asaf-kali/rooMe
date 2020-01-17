package com.example.roome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageViewHolder> {
    private int[] images;
//    private boolean hasMatches = false;

    public RecyclerAdapter(int[] images){
        this.images = images;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_layout, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        int image_id = images[position];
        holder.album.setImageResource(image_id);

        //todo:create dict with image_id:String of apartment info
        holder.albumTitle.setText("Image: " + position); //todo:change to apartment details (location, rent..)
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView album;
        TextView albumTitle;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            album = itemView.findViewById(R.id.iv_album);
            albumTitle = itemView.findViewById(R.id.tv_album_title);
        }
    }
}
