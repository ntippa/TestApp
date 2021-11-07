package com.ntippa.myflickrfindr.network

import com.ntippa.myflickrfindr.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrPhotosApi {

    companion object{
        const val BASE_URL = "https://www.flickr.com/services/rest/"
        private const val API_KEY = BuildConfig.FLICKR_API_KEY
    }


    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    suspend fun searchPhotos(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("text") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): FlickrPhotosResponse
}