package com.ntippa.myflickrfindr.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlickrPhotosResponse(
    val photos: PhotosData
): Parcelable

@Parcelize
data class PhotosData(
    val pages: Int,
    val photo: List<PhotoResponse>
): Parcelable

@Parcelize
data class PhotoResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String
): Parcelable