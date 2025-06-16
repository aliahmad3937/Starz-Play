package com.ali.starzplay.core.domain.usecase

import com.ali.starzplay.core.domain.model.MediaItem
import com.ali.starzplay.core.domain.repository.MediaRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchMediaUseCaseTest {
    private lateinit var repository: MediaRepository
    private lateinit var useCase: SearchMediaUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = SearchMediaUseCase(repository)
    }

    @Test
    fun `invoke returns media items from repository`() = runTest {
        val expected = listOf(
            MediaItem(1, "movie", "Title", "Desc", null, null, false, null)
        )
        coEvery { repository.searchMedia("query") } returns expected

        val result = useCase("query")

        assertEquals(expected, result)
        coVerify { repository.searchMedia("query") }
    }

    @Test
    fun `invoke returns empty list when repository returns empty`() = runTest {
        coEvery { repository.searchMedia("none") } returns emptyList()

        val result = useCase("none")

        assertEquals(emptyList<MediaItem>(), result)
        coVerify { repository.searchMedia("none") }
    }
} 