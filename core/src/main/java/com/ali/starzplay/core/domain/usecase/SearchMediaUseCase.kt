package com.ali.starzplay.core.domain.usecase

import com.ali.starzplay.core.domain.model.MediaItem
import com.ali.starzplay.core.domain.repository.MediaRepository
 
class SearchMediaUseCase(private val repository: MediaRepository) {
    suspend operator fun invoke(query: String): List<MediaItem> {
        return repository.searchMedia(query)
    }
} 