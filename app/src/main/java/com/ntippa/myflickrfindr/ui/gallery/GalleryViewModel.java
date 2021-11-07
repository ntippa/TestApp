package com.ntippa.myflickrfindr.ui.gallery;

import androidx.annotation.NonNull;
import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagingData;

import com.ntippa.myflickrfindr.data.FlickrFindrRepository;
import com.ntippa.myflickrfindr.data.PhotoEntity;
import com.ntippa.myflickrfindr.network.PhotoResponse;

import java.util.List;

public class GalleryViewModel extends ViewModel {

    private FlickrFindrRepository repository;
    public LiveData<PagingData<PhotoResponse>> photos;

    private static final String DEFAULT_QUERY = "flowers";

    MutableLiveData<String> currentQuery = new MutableLiveData(DEFAULT_QUERY);

    @ViewModelInject
    public GalleryViewModel(
            FlickrFindrRepository repository){
        this.repository = repository;
    }

    public LiveData<PagingData<PhotoResponse>> searchPhotos(String query){
        this.currentQuery.setValue(query);
        return Transformations.switchMap(currentQuery,currentQuery -> repository.getSearchResults(currentQuery));

    }

}
