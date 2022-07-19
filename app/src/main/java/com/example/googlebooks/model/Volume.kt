package com.example.googlebooks.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Volume(
    val id: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo
) : Parcelable
