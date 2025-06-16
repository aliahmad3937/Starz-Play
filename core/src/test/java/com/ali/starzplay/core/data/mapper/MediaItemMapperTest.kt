package com.ali.starzplay.core.data.mapper

import com.ali.starzplay.core.data.remote.dto.TmdbMediaItemDto
import com.ali.starzplay.core.domain.model.MediaItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class MediaItemMapperTest {
    @Test
    fun `toDomain returns MediaItem when all required fields are present`() {
        val dto = TmdbMediaItemDto(
            id = 1,
            mediaType = "movie",
            title = "Title",
            name = null,
            overview = "Desc",
            posterPath = "poster.jpg",
            backdropPath = "backdrop.jpg",
            video = true,
            releaseDate = "2020-01-01",
            firstAirDate = null
        )
        val expected = MediaItem(
            id = 1,
            mediaType = "movie",
            title = "Title",
            description = "Desc",
            posterPath = "poster.jpg",
            backdropPath = "backdrop.jpg",
            isVideo = true,
            releaseDate = "2020-01-01"
        )
        assertEquals(expected, dto.toDomain())
    }

    @Test
    fun `toDomain returns null if id or mediaType is missing`() {
        val dtoMissingId = TmdbMediaItemDto(
            id = null,
            mediaType = "movie",
            title = "Title",
            name = null,
            overview = "Desc",
            posterPath = null,
            backdropPath = null,
            video = null,
            releaseDate = null,
            firstAirDate = null
        )
        val dtoMissingType = dtoMissingId.copy(id = 1, mediaType = null)
        assertNull(dtoMissingId.toDomain())
        assertNull(dtoMissingType.toDomain())
    }
} 