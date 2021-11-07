package com.ntippa.myflickrfindr.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoEntity (
    val id: String,
    val title: String,
    val url: String): Parcelable