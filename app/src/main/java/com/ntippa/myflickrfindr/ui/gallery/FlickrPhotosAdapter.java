package com.ntippa.myflickrfindr.ui.gallery;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.ntippa.myflickrfindr.R;
import com.ntippa.myflickrfindr.databinding.ItemPhotoBinding;
import com.ntippa.myflickrfindr.network.PhotoResponse;

public class FlickrPhotosAdapter extends PagingDataAdapter<PhotoResponse, FlickrPhotosAdapter.PhotoViewHolder> {

    private OnItemClickListener itemClickListener;


    public FlickrPhotosAdapter(@NonNull DiffUtil.ItemCallback<PhotoResponse> diffCallback, OnItemClickListener listener) {
        super(diffCallback);
        itemClickListener = listener;

    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPhotoBinding binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        PhotoResponse currentItem = getItem(position);
        if (currentItem != null) {
            holder.bind(currentItem);
        }

    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ItemPhotoBinding viewHolderBinding;
        private ImageView imageView;

        private PhotoViewHolder(ItemPhotoBinding binding) {
            super(binding.getRoot());
            viewHolderBinding = binding;
        }

        public void bind(PhotoResponse photo) {

            Log.d("Adapter", "Photo: " + photo.getTitle());
            Glide.with(viewHolderBinding.view)
                    .load("https://live.staticflickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + "_s.jpg")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_launcher_background)
                    .into(viewHolderBinding.imageviewItem);

            viewHolderBinding.textviewName.setText(photo.getTitle());

            viewHolderBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        PhotoResponse item = getItem(position);
                        if (item != null) {
                            itemClickListener.onItemClick(item);
                        }
                    }
                }
            });
        }
    }

    interface OnItemClickListener {
        public void onItemClick(PhotoResponse photo);
    }

    static class PhotoItemDiff extends DiffUtil.ItemCallback<PhotoResponse> {

        @Override
        public boolean areItemsTheSame(@NonNull PhotoResponse oldItem, @NonNull PhotoResponse newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PhotoResponse oldItem, @NonNull PhotoResponse newItem) {
            return oldItem.equals(newItem);
        }
    }
}
