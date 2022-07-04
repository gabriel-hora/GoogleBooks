package com.example.googlebooks.model

data class Volume(
    val id: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)
