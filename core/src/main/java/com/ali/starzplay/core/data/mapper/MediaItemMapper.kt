package com.ali.starzplay.core.data.mapper

import com.ali.starzplay.core.data.remote.dto.TmdbMediaItemDto
import com.ali.starzplay.core.domain.model.MediaItem

fun TmdbMediaItemDto.toDomain(): MediaItem? {
    val id = this.id ?: return null
    val mediaType = this.mediaType ?: return null
    val title = this.title ?: this.name ?: ""
    val description = this.overview ?: ""
    return MediaItem(
        id = id,
        mediaType = mediaType,
        title = title,
        description = description,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath,
        isVideo = this.video ?: false,
        releaseDate = this.releaseDate ?: this.firstAirDate
    )
} 