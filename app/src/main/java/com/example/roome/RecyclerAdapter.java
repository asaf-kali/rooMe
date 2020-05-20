package com.example.roome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roome.user_classes.RoommateSearcherUser;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This class is a recycler adapter used for the Matches fragment which contains a recycler for
 * viewing all matched apartments
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageViewHolder> {
    private int[] images;

    /**
     * Constructor of the class. Receives an array of ints representing the images
     * @param images
     */
    public RecyclerAdapter(int[] images){
        this.images = images;
    }

    /**
     * on create method for the ImageViewHolder
     * @param parent the parent
     * @param viewType the view type
     * @return an ImageViewHolder
     */
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_layout, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);
        return imageViewHolder;
    }

    /**
     * The method's purpose is to present a matched apartment in the recycler view - the photo of
     * the apartment and some relevant info
     * @param holder ImageViewHolder
     * @param position int representing the position of certain image
     */
    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        int image_id = images[position];
        holder.album.setImageResource(image_id);
        // todo: fix the code below
//        String uid = UsersImageConnector.getUidByImg(image_id);
//        RoommateSearcherUser roommateSearcher = FirebaseMediate.getRoommateSearcherUserByUid(uid);
//        String location = roommateSearcher.getApartment().getNeighborhood();
//        int rent = (int) roommateSearcher.getApartment().getRent();
//        String phone = roommateSearcher.getPhoneNumber();
//        holder.albumTitle.setText("Location: " + location +"\nRent: "+rent+ "\nPhone Number: "+phone);
    }

    /**
     * @return the number of images in the image list
     */
    @Override
    public int getItemCount() {
        return images.length;
    }

    /**
     * Static class representing the album (a matched apartment) and its title (some apartment info)
     */
    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        public ImageView album;
        public TextView albumTitle;

        /**
         * Constructor for the ImageViewHolder class. Binds the fields to their view.
         * @param itemView view object
         */
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            album = itemView.findViewById(R.id.iv_album);
            albumTitle = itemView.findViewById(R.id.tv_album_title);
        }
    }
}
