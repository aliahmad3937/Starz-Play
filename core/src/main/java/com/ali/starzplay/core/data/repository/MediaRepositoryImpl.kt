package com.ali.starzplay.core.data.repository

import com.ali.starzplay.core.data.mapper.toDomain
import com.ali.starzplay.core.data.remote.api.TmdbApi
import com.ali.starzplay.core.domain.model.MediaItem
import com.ali.starzplay.core.domain.repository.MediaRepository

class MediaRepositoryImpl(
    private val api: TmdbApi,
    private val apiKey: String,
    private val apiToken: String
) : MediaRepository {
    override suspend fun searchMedia(query: String): List<MediaItem> {
        val response = api.searchMulti(
            authHeader = "Bearer $apiToken",
            apiKey = apiKey,
            query = query
        )
        return response.results?.mapNotNull { it.toDomain() } ?: emptyList()
    }
} 