package com.ali.starzplay.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.starzplay.core.domain.model.MediaItem
import com.ali.starzplay.core.domain.usecase.SearchMediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class MainScreenUiState(
    val groupedItems: Map<String, List<MediaItem>> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchMediaUseCase: SearchMediaUseCase
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    init {
        _searchQuery
            .debounce(300)
            .filter { it.isNotBlank() }
            .distinctUntilChanged()
            .onEach { _uiState.value = _uiState.value.copy(isLoading = true, error = null) }
            .flatMapLatest { query ->
                flow {
                    val results = searchMediaUseCase(query)
                    emit(results.groupBy { it.mediaType })
                }.catch { e ->
                    emit(emptyMap())
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message ?: "Unknown error"
                    )
                }
            }
            .onEach { grouped ->
                _uiState.value = _uiState.value.copy(
                    groupedItems = grouped,
                    isLoading = false,
                    error = null
                )
            }
            .launchIn(viewModelScope)
        onSearch("tony")
    }

    fun onSearch(query: String) {
        _searchQuery.value = query
    }

} 