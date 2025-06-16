package com.ali.starzplay.ui.viewmodel

import app.cash.turbine.test
import com.ali.starzplay.core.domain.model.MediaItem
import com.ali.starzplay.core.domain.usecase.SearchMediaUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private lateinit var useCase: SearchMediaUseCase
    private lateinit var viewModel: MainViewModel
    private lateinit var testScope: TestScope

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        useCase = mockk()
        viewModel = MainViewModel(useCase)
        testScope = TestScope(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onSearch triggers loading and emits grouped items`() = runTest(dispatcher) {
        val items = listOf(
            MediaItem(1, "movie", "Title1", "Desc1", null, null, false, null),
            MediaItem(2, "tv", "Title2", "Desc2", null, null, false, null)
        )
        coEvery { useCase.invoke("query") } returns items

        viewModel.uiState.test {
            viewModel.onSearch("query")
            // Advance debounce
            dispatcher.scheduler.advanceTimeBy(300)
            // Loading state
            assertEquals(true, awaitItem().isLoading)
            // Grouped result
            val grouped = awaitItem().groupedItems
            assertEquals(2, grouped.size)
            assertEquals(listOf(items[0]), grouped["movie"])
            assertEquals(listOf(items[1]), grouped["tv"])
        }
    }

    @Test
    fun `onSearch emits error on exception`() = runTest(dispatcher) {
        coEvery { useCase.invoke("fail") } throws RuntimeException("Network error")

        viewModel.uiState.test {
            viewModel.onSearch("fail")
            dispatcher.scheduler.advanceTimeBy(300)
            // Loading state
            assertEquals(true, awaitItem().isLoading)
            // Error state
            val errorState = awaitItem()
            assertEquals(false, errorState.isLoading)
            assertEquals("Network error", errorState.error)
        }
    }

    @Test
    fun `onSearch with blank query does not trigger search`() = runTest(dispatcher) {
        viewModel.uiState.test {
            viewModel.onSearch("")
            dispatcher.scheduler.advanceTimeBy(300)
            // Should only emit initial state
            expectNoEvents()
        }
    }

    @Test
    fun `init triggers initial search with tony`() = runTest(dispatcher) {
        val items = listOf(MediaItem(1, "movie", "Title", "Desc", null, null, false, null))
        coEvery { useCase.invoke("tony") } returns items
        val viewModel = MainViewModel(useCase)
        viewModel.uiState.test {
            // Loading state
            assertEquals(true, awaitItem().isLoading)
            // Grouped result
            val grouped = awaitItem().groupedItems
            assertEquals(1, grouped.size)
            assertEquals(listOf(items[0]), grouped["movie"])
        }
    }

    @Test
    fun `onSearch with same query does not trigger duplicate search`() = runTest(dispatcher) {
        val items = listOf(MediaItem(1, "movie", "Title", "Desc", null, null, false, null))
        coEvery { useCase.invoke("query") } returns items
        viewModel.uiState.test {
            viewModel.onSearch("query")
            dispatcher.scheduler.advanceTimeBy(300)
            awaitItem() // loading
            awaitItem() // grouped
            // Repeat same query
            viewModel.onSearch("query")
            dispatcher.scheduler.advanceTimeBy(300)
            // Should not emit new items
            expectNoEvents()
        }
    }

    @Test
    fun `onSearch emits unknown error if exception has no message`() = runTest(dispatcher) {
        coEvery { useCase.invoke("fail") } throws RuntimeException()
        viewModel.uiState.test {
            viewModel.onSearch("fail")
            dispatcher.scheduler.advanceTimeBy(300)
            awaitItem() // loading
            val errorState = awaitItem()
            assertEquals(false, errorState.isLoading)
            assertEquals("Unknown error", errorState.error)
        }
    }
} 