package com.ali.starzplay.core.data.remote.api

import com.ali.starzplay.core.data.remote.dto.TmdbSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TmdbApi {
    @GET("/3/search/multi")
    suspend fun searchMulti(
        @Header("Authorization") authHeader: String,
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): TmdbSearchResponseDto
} 