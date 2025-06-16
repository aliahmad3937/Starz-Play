package com.ali.starzplay.core.domain.model

import android.os.Parcelable
import com.ali.starzplay.core.data.remote.api.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaItem(
    val id: Int,
    val mediaType: String,
    val title: String,
    val description: String,
    val posterPath: String?,
    val backdropPath: String?,
    val isVideo: Boolean,
    val releaseDate: String?,
    val videoUrl:String = Constants.SAMPLE_VIDEO_URL
) : Parcelable

enum class MediaType(){
    MOVIE,
    TV
}