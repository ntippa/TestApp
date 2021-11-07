package com.ntippa.myflickrfindr.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.paging.LoadState;
import androidx.paging.PagingDataAdapter;

import com.ntippa.myflickrfindr.R;
import com.ntippa.myflickrfindr.databinding.FragmentGalleryBinding;
import com.ntippa.myflickrfindr.network.PhotoResponse;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GalleryFragment extends Fragment implements FlickrPhotosAdapter.OnItemClickListener {
    private FragmentGalleryBinding binding;
    private GalleryViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate( inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(GalleryViewModel.class);

        FlickrPhotosAdapter adapter = new FlickrPhotosAdapter(new FlickrPhotosAdapter.PhotoItemDiff(), this);
       // FlickrPhotosAdapter adapter = new FlickrPhotosAdapter(new FlickrPhotosAdapter.PhotoItemDiff());
        binding.recyclerView.setAdapter(adapter);


        if(viewModel != null ) {
            viewModel.searchPhotos("dogs").observe(getViewLifecycleOwner(), photos -> {
                    adapter.submitData(getViewLifecycleOwner().getLifecycle(), photos);
                });
            }

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_gallery, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               if(query != null){
                   binding.recyclerView.scrollToPosition(0);
                   viewModel.searchPhotos(query);
                   searchView.clearFocus();
               }
               return true;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               return true;
           }
       });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(PhotoResponse photo) {
        Log.d("GalleryFragment","ItemClicked");
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(GalleryFragmentDirections.actionGalleryFragmentToDetailFragment(photo));
Toast.makeText(getContext(), "Item Clicked" , Toast.LENGTH_LONG).show();
    }
}
