package com.ali.starzplay.core.di

import com.ali.starzplay.core.data.remote.api.TmdbApi
import com.ali.starzplay.core.data.repository.MediaRepositoryImpl
import com.ali.starzplay.core.domain.repository.MediaRepository
import com.ali.starzplay.core.domain.usecase.SearchMediaUseCase
import com.ali.starzplay.core.data.remote.api.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideTmdbApi(): TmdbApi = Retrofit.Builder()
        .baseUrl(Constants.TMDB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TmdbApi::class.java)

    @Provides
    @Singleton
    fun provideMediaRepository(
        api: TmdbApi
    ): MediaRepository = MediaRepositoryImpl(
        api = api,
        apiKey = Constants.TMDB_API_KEY,
        apiToken = Constants.TMDB_API_TOKEN
    )

    @Provides
    @Singleton
    fun provideSearchMediaUseCase(repository: MediaRepository): SearchMediaUseCase =
        SearchMediaUseCase(repository)
}
