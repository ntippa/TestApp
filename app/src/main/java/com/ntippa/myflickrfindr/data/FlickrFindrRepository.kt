package com.ntippa.myflickrfindr.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ntippa.myflickrfindr.network.FlickrPhotosApi
import com.ntippa.myflickrfindr.network.PhotoResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlickrFindrRepository @Inject constructor(private val flickrApi: FlickrPhotosApi) {

    fun getSearchResults(query: String)  =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FlickrFindrPagingSource(flickrApi, query)}
        ).liveData
}