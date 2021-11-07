package com.ntippa.myflickrfindr.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgs;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.ntippa.myflickrfindr.R;
import com.ntippa.myflickrfindr.databinding.FragmentDetailBinding;
import com.ntippa.myflickrfindr.network.PhotoResponse;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PhotoResponse photoArgs = DetailFragmentArgs.fromBundle(getArguments()).getPhoto();
        binding.textviewDescription.setText(photoArgs.getTitle());
        Glide.with(this)
                .load("https://live.staticflickr.com/" + photoArgs.getServer() + "/" + photoArgs.getId() + "_" + photoArgs.getSecret() + "_s.jpg" )
                . centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageview);

    }
}
