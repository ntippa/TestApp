package com.ntippa.myflickrfindr.data

import androidx.paging.PagingSource
import com.ntippa.myflickrfindr.network.FlickrPhotosApi
import com.ntippa.myflickrfindr.network.PhotoResponse
import retrofit2.HttpException
import java.io.IOException

private const val FLICKR_PAGE_INDEX_START = 1

class FlickrFindrPagingSource(
    private val flickrPhotosApi: FlickrPhotosApi,
    private val query: String
) : PagingSource<Int, PhotoResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponse> {
        val position = params.key ?: FLICKR_PAGE_INDEX_START

        return try {
            val response = flickrPhotosApi.searchPhotos(
                query = query,
                page = position,
                perPage = params.loadSize
            )
            val photos = response.photos.photo

            LoadResult.Page(
                data = photos,
                prevKey = if (position == FLICKR_PAGE_INDEX_START) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch( exception: IOException){
            LoadResult.Error(exception)
        } catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }
}