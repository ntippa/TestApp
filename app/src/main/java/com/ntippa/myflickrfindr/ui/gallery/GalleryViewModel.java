package com.ntippa.myflickrfindr.ui.gallery;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagingData;

import com.ntippa.myflickrfindr.data.FlickrFindrRepository;
import com.ntippa.myflickrfindr.network.PhotoResponse;

public class GalleryViewModel extends ViewModel {

    private SavedStateHandle state;
    private FlickrFindrRepository repository;
    private static final String DEFAULT_QUERY = "flowers";
    private static final String CURRENT_QUERY = "query";

    private MutableLiveData<String> currentQuery = new MutableLiveData(DEFAULT_QUERY);


    public LiveData<PagingData<PhotoResponse>> photos =
            Transformations.switchMap(currentQuery,currentQuery -> repository.getSearchResults(currentQuery));



    @ViewModelInject
    public GalleryViewModel(
            FlickrFindrRepository repository){
        this.repository = repository;
    }

//    public LiveData<PagingData<PhotoResponse>> searchPhotos(String query){
//        setQuery(query);
//        return Transformations.switchMap(currentQuery,currentQuery -> repository.getSearchResults(currentQuery));
//
//    }

    void setQuery(String query){
        this.currentQuery.setValue(query);
    }


}
