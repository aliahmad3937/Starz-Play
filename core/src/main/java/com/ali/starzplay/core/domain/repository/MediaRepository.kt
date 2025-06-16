package com.ali.starzplay.core.domain.repository

import com.ali.starzplay.core.domain.model.MediaItem
 
interface MediaRepository {
    suspend fun searchMedia(query: String): List<MediaItem>
} 