package com.ntippa.myflickrfindr.di

import com.ntippa.myflickrfindr.network.FlickrPhotosApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(FlickrPhotosApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideFlickrPhotosApi(retrofit: Retrofit): FlickrPhotosApi =
        retrofit.create(FlickrPhotosApi::class.java)
}