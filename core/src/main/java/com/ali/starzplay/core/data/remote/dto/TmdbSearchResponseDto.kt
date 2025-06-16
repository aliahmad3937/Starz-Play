package com.ali.starzplay.core.data.remote.dto

import com.google.gson.annotations.SerializedName

// Root response
data class TmdbSearchResponseDto(
    @SerializedName("page") val page: Int?,
    @SerializedName("total_results") val totalResults: Int?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("results") val results: List<TmdbMediaItemDto>?
)

// Media item DTO
data class TmdbMediaItemDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("media_type") val mediaType: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("first_air_date") val firstAirDate: String?,
    @SerializedName("release_date") val releaseDate: String?
)